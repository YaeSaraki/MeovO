package dev.saraki.meovo

import dev.saraki.meovo.module.yawebapi.YaWebApiModule
import dev.saraki.meovo.module.nomobgrief.NoMobGriefModule
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.module.lang.Language
import taboolib.platform.bukkit.Exchanges.ExchangePlugin.saveDefaultConfig

object MeovoPlugin : Plugin() {
    lateinit var pluginConfigDir: String // 插件配置目录（供 Spring Boot 使用）
    override fun onLoad() {
        Language.default = "zh_CN"
    }

    override fun onEnable() {
        val dataFolder = getDataFolder()
        pluginConfigDir = dataFolder.absolutePath
        saveDefaultConfig()

        NoMobGriefModule.onEnable(this)
        YaWebApiModule.onEnable(this)
    }

    override fun onDisable() {
        NoMobGriefModule.onDisable(this)
        YaWebApiModule.onDisable(this)
    }
}

