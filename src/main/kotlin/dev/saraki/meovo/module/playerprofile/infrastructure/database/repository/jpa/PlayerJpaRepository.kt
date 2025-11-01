package dev.saraki.meovo.module.playerprofile.infrastructure.database.repository.jpa

import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PlayerJpaRepository : JpaRepository<PlayerTable, UUID> {
}