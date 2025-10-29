package dev.saraki.meovo.module.playerprofile

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console

object PlayerProfileModule {
    fun initialize(plugin: Plugin) {
        console().sendMessage("[Meovo] PlayerProfile 模块已加载")
    }
}