package dev.saraki.meovo.module.nomobgrief.config

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

/**
 * 配置类：模拟"末影人可拾取方块标签"的自定义配置
 * 替代修改 Minecraft 内置的 #minecraft:enderman_holdable 标签
 */
object EndermanConfig {
    @Config("config/NoMobGrief/enderman.yml", autoReload = true)
    lateinit var config : ConfigFile

    val enableControl: Boolean
        get() = config.getBoolean("enable-control", true)

    val mode: String?
        get() = config.getString("mode", "BLACKLIST")?.uppercase()

    val customHoldableBlocks: List<String>
        get() = config.getStringList("custom-holdable-blocks")

    val exceptionWorlds: List<String>
        get() = config.getStringList("exception-worlds")
}
