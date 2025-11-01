package dev.saraki.meovo.module.playerprofile.application.service

import dev.saraki.meovo.module.playerprofile.domain.entity.AdvancementInfo
import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerInfoTable
import dev.saraki.meovo.module.playerprofile.domain.repository.PlayerRepository
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.AdvancementInfoTable
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerTable
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.advancement.AdvancementProgress
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID


@Service
class PlayerDataService(
    val playerRepository: PlayerRepository
) {
    fun getPlayerBaseInfo(uuid: UUID): PlayerBaseInfo? {
        return playerRepository.findPlayerInfo(uuid)?.playerBaseInfo
    }

    fun updatePlayerData(uuid: UUID) {
        try {
        val player = Bukkit.getPlayer(uuid) ?: return
            updatePlayerData(player)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updatePlayerData(player: Player): Boolean {
        val uuid = player.uniqueId
        val p = playerRepository.findPlayerInfo(uuid)
        if (p == null) {
            val newPlayerTable = PlayerTable(
                playerUuid = uuid,
                lastLoginTime = LocalDateTime.now(),
            )
            val newPlayerInfoTable = PlayerInfoTable(
                playerUuid = uuid,
                player = newPlayerTable,
                playerBaseInfo = PlayerBaseInfo(
                    name = player.displayName,
                    firstLoginTime = LocalTime.now()
                ),
            )
            playerRepository.addPlayer(newPlayerTable, newPlayerInfoTable)
        }
        else {
            p.player.lastLoginTime = LocalDateTime.now()
        }
        return false
    }

    fun adapter(progress: AdvancementProgress): AdvancementInfo  {
        val advancement = progress.advancement
        return AdvancementInfoTable(
            id = AdvancementId(advancement.display?.title.toString()),
            description = advancement.display?.description ?: "No description",
        )
    }


}
