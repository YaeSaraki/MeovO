package dev.saraki.meovo.module.nomobgrief.listener

import dev.saraki.meovo.module.nomobgrief.logic.EndermanLogic
import org.bukkit.entity.Enderman
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityChangeBlockEvent

object EndermanPickupListener {

    // 监听末影人拾取方块事件（破坏方块准备拾取时触发）
    @EventHandler
    fun onEndermanPickup(event: EntityChangeBlockEvent) {
        val enderman = event.entity as? Enderman ?: return
        val world = event.block.world
        val blockMaterial = event.block.type

        // 3. 调用逻辑类判断：是否禁止拾取
        if (!EndermanLogic.isPickupAllowed(world, blockMaterial)) {
            event.isCancelled = true // 阻止拾取
        }
    }
}