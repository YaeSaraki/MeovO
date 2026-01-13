package dev.saraki.meovo.module.yawebapi

import asia.minecraftserver.yawebapi.WebServer
import dev.saraki.meovo.module.Module
import dev.saraki.meovo.module.yawebapi.config.YaWebApiConfig
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.env.RuntimeDependencies
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.Plugin
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info

@RuntimeDependencies(
    RuntimeDependency(
        value = "!com.google.code.gson:gson:2.10.1",
    ),
    RuntimeDependency(
        value = "!org.nanohttpd:nanohttpd:2.3.1",
    )
)
object YaWebApiModule : Module() {

    private lateinit var server: WebServer

    override fun onEnable(plugin: Plugin) {
        super.onEnable(plugin)

        info("Starting REST API server...")
        val targetServerPort = YaWebApiConfig.getTargetServerPort
        val targetServerHost = YaWebApiConfig.getTargetServerHost
        try {
            info("Starting REST API server on $targetServerHost:$targetServerPort...")
            server = WebServer(targetServerPort, targetServerHost)
            server.start()

        } catch (e: Exception) {
            info("Failed to start REST API server: ${e.message}")
        }
    }
    override fun onDisable(plugin: Plugin) {
        super.onDisable(plugin)
        try {
            if (::server.isInitialized) {
                server.stop()
                info("Stopped REST API server")
            }
        } catch (e: Exception) {
            info("Failed to stop REST API server: ${e.message}")
        }
    }

    /**
     * 此类仅在 Bukkit 平台下会被加载
     */
    @PlatformSide(Platform.BUKKIT)
    object BukkitListener {

        @SubscribeEvent
        fun onPlayerJoin(event: PlayerJoinEvent) {
            // 玩家加入时执行
        }
    }
}
