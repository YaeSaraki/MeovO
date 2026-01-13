package dev.saraki.meovo.module.nomobgrief.logic

import dev.saraki.meovo.module.nomobgrief.config.EndermanConfig
import org.bukkit.Material
import org.bukkit.World

/**
 * 末影人拾取逻辑类：适配配置类的黑白名单模式，拆分核心判断功能
 * 规则优先级：全局开关关闭 > 例外世界 > 黑白名单模式校验
 */
object EndermanLogic {

    /**
     * 总入口：判断是否允许末影人拾取方块
     * @param world 末影人所在世界
     * @param blockMaterial 被拾取方块材质
     * @return true=允许拾取，false=禁止拾取
     */
    fun isPickupAllowed(world: World, blockMaterial: Material): Boolean {
        // 1. 先判断全局控制是否关闭（关闭则直接允许）
        if (!EndermanConfig.enableControl) return true
        // 2. 再判断是否为例外世界（例外世界直接允许）
        if (isWorldInException(world)) return true
        // 3. 最后按黑白名单模式校验方块
        return when (EndermanConfig.mode) {
            "WHITELIST" -> isBlockInWhitelist(blockMaterial)  // 白名单：仅允许列表内方块
            "BLACKLIST" -> !isBlockInBlacklist(blockMaterial) // 黑名单：禁止列表内方块
            else -> true // 模式配置错误时，默认允许（避免误拦截）
        }
    }

    /**
     * 功能1：判断世界是否在「例外世界」列表（不受控制影响）
     * 支持通配符（如配置 "world*" 匹配 "world" 和 "world_nether"）
     */
    private fun isWorldInException(world: World): Boolean {
        val exceptionWorlds = EndermanConfig.exceptionWorlds
        val worldName = world.name
        // 精确匹配（如 "lobby"）
        if (exceptionWorlds.contains(worldName)) return true
        // 通配符匹配（如 "world*"）
        return exceptionWorlds.any {
            it.endsWith("*") && worldName.startsWith(it.removeSuffix("*"))
        }
    }

    /**
     * 功能2：判断方块是否在「白名单」（仅 WHITELIST 模式生效）
     * 忽略配置文件中的大小写（支持 "GRASS_BLOCK" 和 "grass_block"）
     */
    private fun isBlockInWhitelist(blockMaterial: Material): Boolean {
        val whitelist = EndermanConfig.customHoldableBlocks
        return whitelist.any {
            it.equals(blockMaterial.name, ignoreCase = true)
        }
    }

    /**
     * 功能3：判断方块是否在「黑名单」（仅 BLACKLIST 模式生效）
     * 逻辑与白名单一致，仅用途不同
     */
    private fun isBlockInBlacklist(blockMaterial: Material): Boolean {
        val blacklist = EndermanConfig.customHoldableBlocks
        return blacklist.any {
            it.equals(blockMaterial.name, ignoreCase = true)
        }
    }

    /**
     * 辅助功能：获取当前生效模式的描述（用于命令反馈/日志）
     */
    fun getCurrentModeDesc(): String {
        return when (EndermanConfig.mode) {
            "WHITELIST" -> "白名单模式（仅允许 [${EndermanConfig.customHoldableBlocks.joinToString(", ")}]）"
            "BLACKLIST" -> "黑名单模式（禁止 [${EndermanConfig.customHoldableBlocks.joinToString(", ")}]）"
            else -> "模式配置错误（当前：${EndermanConfig.mode ?: "未设置"}，请检查 enderman.yml）"
        }
    }
}