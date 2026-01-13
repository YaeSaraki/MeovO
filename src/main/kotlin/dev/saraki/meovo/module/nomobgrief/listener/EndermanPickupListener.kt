package dev.saraki.meovo.module.nomobgrief.listener

import dev.saraki.meovo.module.nomobgrief.logic.EndermanLogic
import org.bukkit.entity.Enderman
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent

object EndermanPickupListener: Listener {
    @EventHandler
    fun onEndermanPickup(event: EntityChangeBlockEvent) {
        if (event.entity !is Enderman) return

        val world = event.block.world
        val blockMaterial = event.block.type
        if (!EndermanLogic.isPickupAllowed(world, blockMaterial)) {
            event.isCancelled = true
        }
    }
}