package dev.saraki.meovo.module.nomobgrief.logic

import dev.saraki.meovo.module.nomobgrief.config.CreeperConfig

// 领域逻辑：封装“是否允许破坏”的核心规则
object CreeperLogic {
    /**
     * @function 判断是否允许苦力怕爆炸破坏方块
     * @param worldName 世界名称
     * @param isPowered 是否为闪电苦力怕
     */
    fun isDestructionAllowed(worldName: String, isPowered: Boolean): Boolean {
        // 直接使用配置类的值（轻量模式下允许领域逻辑依赖配置）
        if (CreeperConfig.allowedWorlds.contains(worldName)) return true
        if (CreeperConfig.allowDestruction) return true
        if (isPowered && CreeperConfig.allowPoweredDestruction) return true
        return false
    }
}