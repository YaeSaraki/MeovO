package dev.saraki.meovo.module.nomobgrief.config

import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import java.io.File

object CreeperConfig {
    // 1. 框架只负责“文件存在性 + 热重载”，不注入字段
    @Config("config/NoMobGrief/creeper.yml", autoReload = true)
    private val dummy = Unit

    // 2. 真正的 Configuration 在第一次被访问时才加载
    private val config by lazy {
        Configuration.loadFromFile(File(getDataFolder(), "config/NoMobGrief/creeper.yml"))
    }
    val allowDestruction: Boolean
        get() = config.getBoolean("allow-destruction", false)

    val allowedWorlds: List<String>
        get() = config.getStringList("allowed-worlds")

    val allowPoweredDestruction: Boolean
        get() = config.getBoolean("allow-powered-destruction", false)
}