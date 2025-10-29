package dev.saraki.meovo

import dev.saraki.meovo.module.nomobgrief.NoMobGriefModule
import dev.saraki.meovo.module.playerprofile.PlayerProfileModule
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.module.lang.Language
import taboolib.module.lang.sendLang
import taboolib.platform.bukkit.Exchanges.ExchangePlugin.saveDefaultConfig
import taboolib.platform.bukkit.Exchanges.ExchangePlugin.saveResource
import java.io.File

object MeovoPlugin : Plugin() {
    lateinit var pluginConfigDir: String // 插件配置目录（供 Spring Boot 使用）
    override fun onLoad() {
        // 初始化默认语言（配合多语言模块）
        Language.default = "zh_CN"
    }

    override fun onEnable() {
        // 1. 初始化插件配置目录（plugins/MeovO/）
        val dataFolder = getDataFolder()
        pluginConfigDir = dataFolder.absolutePath
        console().sendLang("plugin.enabled", "配置目录: $pluginConfigDir")
        saveDefaultConfig()
        // 3. 启动 Spring Boot，传入插件配置目录
        startSpringBootWithConfig()

        NoMobGriefModule.initialize(this)
        PlayerProfileModule.initialize(this)
        console().sendLang("plugin.enabled")
    }

    override fun onDisable() {
    }

    // 复制默认配置文件到插件目录
    private fun saveDefaultConfig() {
        val configFile = File(getDataFolder(), "application.properties")
        if (!configFile.exists()) {
            saveResource("application.properties", false) // 从 resources 复制默认配置
        }
    }

    // 启动 Spring Boot 并指定外部配置路径
    private fun startSpringBootWithConfig() {
        // 设置 Spring Boot 读取插件目录下的配置文件
        System.setProperty("spring.config.location", "file:$pluginConfigDir/application.properties")
        // 调用 Spring Boot 启动方法
//        main(arrayOf())
    }
}

