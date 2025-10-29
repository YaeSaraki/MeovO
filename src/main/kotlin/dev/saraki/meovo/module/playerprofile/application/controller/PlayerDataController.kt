package dev.saraki.meovo.module.playerprofile.application.controller

import dev.saraki.meovo.module.playerprofile.application.service.PlayerDataService
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 14:00
 *   @description:
 */
@RestController
@RequestMapping("/api/players")
class PlayerDataController (
    private val playerDataService: PlayerDataService
){
    @GetMapping("/update/{uuid}")
    fun updatePlayerData(@PathVariable uuid: String) {
        val uuid = UUID.fromString(uuid)
        playerDataService.updatePlayerData(uuid)
    }

    @GetMapping("/{uuid}")
    fun getPlayerData(@PathVariable uuid: String): PlayerBaseInfo? {
        return playerDataService.getPlayerInfo(UUID.fromString(uuid))
    }
}