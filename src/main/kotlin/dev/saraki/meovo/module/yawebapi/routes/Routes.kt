package dev.saraki.meovo.module.yawebapi.routes

import fi.iki.elonen.NanoHTTPD

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2026/1/12 14:27
 *   @description:
 */
class Routes {
    fun handle(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        val uri = session.uri
        return when {
            // 成就路由
            uri == "/api/v1/advancements" -> AdvancementRoutes.handleAllOnlinePlayer(session)
            uri.startsWith("/api/v1/advancements/") -> AdvancementRoutes.handleSingleOnlinePlayer(session)

            // 统计信息路由
            uri == "/api/v1/statistics" -> StatisticRoutes.handleAllOnlinePlayer(session)
            uri.startsWith("/api/v1/statistics/") -> StatisticRoutes.handleSingleOnlinePlayer(session)

            // 其他路由
            else -> NanoHTTPD.newFixedLengthResponse(
                NanoHTTPD.Response.Status.NOT_FOUND,
                "application/json",
                """{"error":"Not Found"}"""
            )
        }
    }
}

