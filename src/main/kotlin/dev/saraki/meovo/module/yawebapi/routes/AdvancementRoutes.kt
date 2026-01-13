package dev.saraki.meovo.module.yawebapi.routes

import dev.saraki.meovo.module.yawebapi.tools.RouteTools.addAllowOriginsHeader
import dev.saraki.meovo.module.yawebapi.tools.RouteTools.jsonResponseWithAllowOriginsHeader
import fi.iki.elonen.NanoHTTPD
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.function.info

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2026/1/12 15:30
 *   @description:
 */
object AdvancementRoutes {
        // 获取所有玩家成就
        fun handleAllOnlinePlayer(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
            val players = Bukkit.getOnlinePlayers()
            val data = players.map { player -> buildPlayerAdvancements(player) }
            val response = jsonResponseWithAllowOriginsHeader(data)
            return response
        }

        // 获取单个玩家成就
        fun handleSingleOnlinePlayer(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
            val playerName = session.uri.substringAfter("/api/v1/advancements/")
            val player = Bukkit.getPlayerExact(playerName)
            info("AdvancementRoutes.handleSingle: $playerName")

            if (player == null) {
                val response = NanoHTTPD.newFixedLengthResponse(
                    NanoHTTPD.Response.Status.NOT_FOUND,
                    "application/json",
                    """{"error":"Player not found"}"""
                )
                addAllowOriginsHeader(response)
                return response
            }

            val data = buildPlayerAdvancements(player)
            val response = jsonResponseWithAllowOriginsHeader(data)
            return response
        }

        // 构建玩家成就信息
        private fun buildPlayerAdvancements(player: Player): Map<String, Any> {
            val advancements = Bukkit.advancementIterator().asSequence().map { adv ->
                val progress = player.getAdvancementProgress(adv)
                mapOf(
                    "advancement" to adv.key.key,
                    "done" to progress.isDone,
                    "completed" to progress.awardedCriteria,
                    "remaining" to progress.remainingCriteria
                )
            }.toList()

            return mapOf(
                "uuid" to player.uniqueId.toString(),
                "name" to player.name,
                "advancements" to advancements
            )
        }

}