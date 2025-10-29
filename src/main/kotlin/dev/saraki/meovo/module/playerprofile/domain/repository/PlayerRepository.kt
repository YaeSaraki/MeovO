package dev.saraki.meovo.module.playerprofile.domain.repository

import dev.saraki.meovo.module.playerprofile.infrastructure.database.PlayerTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 02:07
 *   @description:
 */
@Repository
interface PlayerRepository : JpaRepository<PlayerTable, UUID> {
}