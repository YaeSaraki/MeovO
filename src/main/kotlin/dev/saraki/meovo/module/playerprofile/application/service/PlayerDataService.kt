package dev.saraki.meovo.module.playerprofile.application.service

import dev.saraki.meovo.module.playerprofile.domain.entity.AdvancementInfo
import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import dev.saraki.meovo.module.playerprofile.infrastructure.database.PlayerInfoTable
import dev.saraki.meovo.module.playerprofile.domain.repository.PlayerRepository
import org.bukkit.Bukkit
import org.bukkit.Bukkit.advancementIterator
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.entity.Player
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.UUID


@Service
class PlayerDataService(
    val playerRepository: PlayerRepository
) {
    fun getPlayerInfo(uuid: UUID): PlayerBaseInfo? {
        return playerRepository.findById(uuid).orElse(null)?.playerInfoTable?.playerBaseInfo
    }

    fun updatePlayerData(uuid: UUID) {
        val player = Bukkit.getPlayer(uuid) ?: return
        updatePlayerData(player)
    }

    fun updatePlayerData(player: Player): Boolean {
        val uuid = player.uniqueId
        val p = playerRepository.findById(uuid).orElse(null)
        if (p == null) {
            val newPlayerTable = Player(
                playerUuid = uuid,
                playerInfoTable = PlayerInfoTable(
                    playerUuid = uuid,
                    playerBaseInfo = PlayerBaseInfo(
                        name = player.name,
                        firstLoginTime = LocalTime.now()
                    ),
                    lastLoginTime = LocalDateTime.now(),
                ),
                lastLoginTime = LocalDateTime.now(),
                advancements = mutableSetOf(),
                )
            playerRepository.save(newPlayerTable)
        }
        val oldHash = p?.advancements.hashCode()

        val advancements: MutableSet<AdvancementInfo> = mutableSetOf()
        for (advancement in advancementIterator()) {
            val progress = player.getAdvancementProgress(advancement)
            if (progress.isDone) {
                advancements.add(adapter(progress))
            }
        }
        if (oldHash != advancements.hashCode()) {
            p.advancements = advancements
            playerRepository.save(p)
            return true
        }
        return false
    }

    fun adapter(progress: AdvancementProgress): AdvancementInfo  {
        val advancement = progress.advancement
        return AdvancementInfo(
            id = AdvancementId(advancement.display?.title.toString()),
            description = advancement.display?.description ?: "No description",
            date = Date(Date().time),
        )
    }


}
