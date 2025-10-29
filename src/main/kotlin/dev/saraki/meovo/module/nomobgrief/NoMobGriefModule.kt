package dev.saraki.meovo.module.nomobgrief


import dev.saraki.meovo.module.nomobgrief.config.CreeperConfig
import dev.saraki.meovo.module.nomobgrief.config.EndermanConfig
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console

object NoMobGriefModule {
    fun initialize(plugin: Plugin) {
        console().sendMessage("[Meovo] NoMobGrief 模块已加载")
    }
}