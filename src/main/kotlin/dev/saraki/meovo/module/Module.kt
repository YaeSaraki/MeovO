package dev.saraki.meovo.module

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2026/1/13 11:10
 *   @description:
 */
abstract class Module {

    public open fun onEnable(plugin: Plugin) {
        info("Loading ${this::class.simpleName}...")
    }
    public open fun onDisable(plugin: Plugin) {
        info("Loading ${this::class.simpleName}...")
    }
}