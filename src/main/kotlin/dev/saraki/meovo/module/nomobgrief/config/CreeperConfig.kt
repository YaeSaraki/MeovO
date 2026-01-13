package dev.saraki.meovo.module.nomobgrief.config

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object CreeperConfig {
    @Config("config/NoMobGrief/creeper.yml", autoReload = true)
    lateinit var config : ConfigFile

    val allowDestruction: Boolean
        get() = config.getBoolean("allow-destruction", false)

    val allowPoweredDestruction: Boolean
        get() = config.getBoolean("allow-powered-destruction", false)

    val allowedWorlds: List<String>
        get() = config.getStringList("allowed-worlds")
}