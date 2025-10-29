package dev.saraki.meovo.module.playerprofile.domain.repository

import dev.saraki.meovo.module.playerprofile.domain.entity.AdvancementInfo
import dev.saraki.meovo.module.playerprofile.infrastructure.database.PlayerTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 02:08
 *   @description:
 */
@Repository
interface AdvancementsRepository : JpaRepository<PlayerTable, AdvancementInfo> {
}