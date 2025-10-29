package dev.saraki.meovo.module.playerprofile.infrastructure.bukkit.listener

import dev.saraki.meovo.module.playerprofile.application.service.PlayerDataService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerStatisticIncrementEvent

class GameEventHandler(
    private val playerDataService: PlayerDataService
){

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        playerDataService.updatePlayerData(event.player)
    }

    @EventHandler
    fun onPlayerStatisticIncrement(event: PlayerStatisticIncrementEvent) {
        val player = event.player
        val amount = event.newValue

    }

    @EventHandler
    fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        val advancement = event.advancement
        val player = event.player

        val progress = player.getAdvancementProgress(advancement)

    }
}