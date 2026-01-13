package asia.minecraftserver.yawebapi

import dev.saraki.meovo.module.yawebapi.routes.Routes
import fi.iki.elonen.NanoHTTPD
import taboolib.common.platform.function.warning

/**
*   @author YaeSaraki 
*   @email ikaraswork@iCloud.com
*   @date 2026/1/12 10:43
*   @description: 
*/
class WebServer(port: Int, private val allowHost: String) : NanoHTTPD(port) {

    private val Routes = Routes()

    override fun serve(session: IHTTPSession): Response {
        val host = session.remoteIpAddress
        if (host != allowHost) {
            warning("Server host doesn't match allowed host! $host != $allowHost")
            return newFixedLengthResponse(Response.Status.FORBIDDEN, MIME_PLAINTEXT, "Host not allowed")
        }
        val uri = session.uri
        return when {
            uri.startsWith("/api/v1/") -> Routes.handle(session)
            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, "application/json", """{"error":"Not Found"}""")
        }
    }
}
