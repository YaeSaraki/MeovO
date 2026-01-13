package asia.minecraftserver.yawebapi.routes

import dev.saraki.meovo.module.yawebapi.tools.RouteTools.addAllowOriginsHeader
import com.google.gson.Gson
import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.newFixedLengthResponse
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.function.info

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2026/1/12 10:55
 *   @description:
 */
object PlayerRoutes {
    private val gson = Gson()

    fun handle(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        val uri = session.uri
        val playerName = uri.substringAfter("/api/v1/players/")
        val player: Player? = Bukkit.getPlayerExact(playerName)
        info("PlayerRoute.handle: $playerName")

        if (player == null) {
            val response = newFixedLengthResponse(NanoHTTPD.Response.Status.NOT_FOUND, "application/json", """{"error":"Player not found"}""")
            // 跨域请求
            addAllowOriginsHeader(response)
            return response
        }

        val response = newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "application/json", gson.toJson(player))
        // 跨域请求
        addAllowOriginsHeader(response)
        response.addHeader("Access-Control-Allow-Methods", "GET, POST")
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")

        return response
    }



}