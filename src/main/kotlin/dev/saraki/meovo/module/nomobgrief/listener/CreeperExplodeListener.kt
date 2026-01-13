package dev.saraki.meovo.module.nomobgrief.listener

import dev.saraki.meovo.module.nomobgrief.logic.CreeperLogic
import org.bukkit.entity.Creeper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent

object CreeperExplodeListener: Listener {
    @EventHandler
    fun onExplode(event: EntityExplodeEvent) {
        val creeper = event.entity as? Creeper ?: return
        val worldName = event.location.world?.name ?: return

        if (!CreeperLogic.isDestructionAllowed(worldName, creeper.isPowered)) {
            event.blockList().clear()
        }
    }
}