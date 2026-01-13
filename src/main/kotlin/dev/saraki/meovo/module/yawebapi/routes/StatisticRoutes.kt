package dev.saraki.meovo.module.yawebapi.routes

import dev.saraki.meovo.module.yawebapi.tools.RouteTools
import dev.saraki.meovo.module.yawebapi.tools.RouteTools.jsonResponseWithAllowOriginsHeader
import fi.iki.elonen.NanoHTTPD
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import taboolib.common.platform.function.info

object StatisticRoutes {

    // 获取所有玩家统计信息
    fun handleAllOnlinePlayer(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        val params = session.parameters
        val filterType = params["type"]?.firstOrNull()
        val category = params["category"]?.firstOrNull()
        val entity = params["entity"]?.firstOrNull()

        val players = Bukkit.getOnlinePlayers()
        val data = players.map { player ->
            buildPlayerStatistics(player, filterType, category, entity)
        }
        return jsonResponseWithAllowOriginsHeader(data)
    }

    // 获取单个玩家统计信息
    fun handleSingleOnlinePlayer(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        val playerName = session.uri.substringAfter("/api/v1/statistics/")
        val player = Bukkit.getPlayerExact(playerName)
        info("StatisticRoutes.handleSingle: $playerName")

        if (player == null) {
            val response = NanoHTTPD.newFixedLengthResponse(
                NanoHTTPD.Response.Status.NOT_FOUND,
                "application/json",
                """{"error":"Player not found"}"""
            )
            RouteTools.addAllowOriginsHeader(response)
            return response
        }

        val params = session.parameters
        val filterType = params["type"]?.firstOrNull()
        val category = params["category"]?.firstOrNull()
        val entity = params["entity"]?.firstOrNull()

        val data = buildPlayerStatistics(player, filterType, category, entity)
        return jsonResponseWithAllowOriginsHeader(data)
    }

    private fun buildPlayerStatistics(
        player: Player,
        filterType: String?,   // 例如: JUMP、MINE_BLOCK、KILL_ENTITY
        category: String?,     // 对 BLOCK/ITEM 的模糊过滤，如 "STONE"、"DIAMOND"
        entity: String?,       // 对 ENTITY 的精确过滤，如 "ZOMBIE"
        material: String? = null // 对 BLOCK/ITEM 的精确过滤，如 "STONE"
    ): Map<String, Any> {

        val statistics = mutableMapOf<String, Any>()

        Statistic.values().forEach { stat ->
            // 如果指定了具体的统计项名，只返回匹配的
            if (filterType != null && !stat.name.equals(filterType, ignoreCase = true)) return@forEach

            try {
                val value: Any = when (stat.type) {
                    Statistic.Type.UNTYPED -> {
                        // 直接取值
                        player.getStatistic(stat)
                    }

                    Statistic.Type.ITEM -> {
                        // 只遍历可作为物品的 Material
                        val items = Material.entries.asSequence()
                            .filter { it.isItem }
                            .filter { material == null || it.name.equals(material, true) }
                            .filter { category == null || it.name.contains(category, true) }
                            .associate { mat -> mat.name to player.getStatistic(stat, mat) }
                        items
                    }

                    Statistic.Type.BLOCK -> {
                        // 只遍历可作为方块的 Material
                        val blocks = Material.entries.asSequence()
                            .filter { it.isBlock }
                            .filter { material == null || it.name.equals(material, true) }
                            .filter { category == null || it.name.contains(category, true) }
                            .associate { mat -> mat.name to player.getStatistic(stat, mat) }
                        blocks
                    }

                    Statistic.Type.ENTITY -> {
                        val ents = EntityType.entries.asSequence()
                            .filter { entity == null || it.name.equals(entity, true) }
                            .associate { ent -> ent.name to player.getStatistic(stat, ent) }
                        ents
                    }
                }

                statistics[stat.name] = value
            } catch (e: Exception) {
                // 防御式：单项失败不影响整体
                statistics[stat.name] = "error: ${e.message}"
            }
        }

        return mapOf(
            "uuid" to player.uniqueId.toString(),
            "name" to player.name,
            "statistics" to statistics
        )
    }
}
