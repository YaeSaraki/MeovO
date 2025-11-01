package dev.saraki.meovo.module.playerprofile.infrastructure.database.repository.impl

import dev.saraki.meovo.module.playerprofile.domain.repository.PlayerRepository
import dev.saraki.meovo.module.playerprofile.infrastructure.database.repository.jpa.PlayerInfoJpaRepository
import dev.saraki.meovo.module.playerprofile.infrastructure.database.repository.jpa.PlayerJpaRepository
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerInfoTable
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerTable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PlayerRepositoryImpl(
    private val playerJpaRepository: PlayerJpaRepository,
    private val playerInfoJpaRepository: PlayerInfoJpaRepository
)
    : PlayerRepository {
    override fun findPlayerInfo(uuid: UUID): PlayerInfoTable? {
        return playerInfoJpaRepository.findById(uuid).orElse(null)
    }

    override fun addPlayer(
        playerTable: PlayerTable,
        playerInfoTable: PlayerInfoTable
    ): Boolean {
        playerJpaRepository.save(playerTable)
        playerInfoJpaRepository.save(playerInfoTable)
        return true
    }

}