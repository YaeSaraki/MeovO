import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object GlobalConfig {
    @Config("config/config.yml", autoReload = true)
    lateinit var config: Configuration
        private set

    // 全局配置属性示例
    val debugMode: Boolean
        get() = config.getBoolean("debug", false)
}
