package dev.saraki.meovo.module.yawebapi.tools

import dev.saraki.meovo.module.yawebapi.config.YaWebApiConfig
import com.google.gson.Gson
import fi.iki.elonen.NanoHTTPD

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2026/1/12 15:36
 *   @description:
 */
object RouteTools {
    fun addAllowOriginsHeader(response: NanoHTTPD.Response) {
        val allowedOrigins = YaWebApiConfig.getAllowedOrigins
        for (origin in allowedOrigins) {
            response.addHeader("Access-Control-Allow-Origin", origin)
        }
    }

    fun jsonResponseWithAllowOriginsHeader(data: Any): NanoHTTPD.Response {
        val json = Gson().toJson(data)
        val response = NanoHTTPD.newFixedLengthResponse(
            NanoHTTPD.Response.Status.OK,
            "application/json",
            json
        )

        addAllowOriginsHeader(response)
        return response
    }
}