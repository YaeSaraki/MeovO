package dev.saraki.meovo.module.nomobgrief.config

import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import java.io.File

/**
 * 配置类：模拟"末影人可拾取方块标签"的自定义配置
 * 替代修改 Minecraft 内置的 #minecraft:enderman_holdable 标签
 */
object EndermanConfig {

    // 1. 框架只负责“文件存在性 + 热重载”，不注入字段
    @Config("config/NoMobGrief/enderman.yml", autoReload = true)
    private val dummy = Unit

    // 2. 真正的 Configuration 在第一次被访问时才加载
    private val config by lazy {
        Configuration.loadFromFile(File(getDataFolder(), "config/NoMobGrief/enderman.yml"))
    }

    // 全局开关：是否启用拾取控制
    val enableControl: Boolean
        get() = config.getBoolean("enable-control", true)

    // 模式：WHITELIST（仅允许列表内方块）/ BLACKLIST（禁止列表内方块）
    val mode: String?
        get() = config.getString("mode", "BLACKLIST")?.uppercase()

    // 自定义可拾取方块列表（模拟标签内容）
    val customHoldableBlocks: List<String>
        get() = config.getStringList("custom-holdable-blocks")

    // 例外世界：不受拾取控制影响的世界
    val exceptionWorlds: List<String>
        get() = config.getStringList("exception-worlds")
}
