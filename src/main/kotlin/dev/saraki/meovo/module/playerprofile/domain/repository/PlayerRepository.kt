package dev.saraki.meovo.module.playerprofile.domain.repository

import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerInfoTable
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerTable
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 02:07
 *   @description:
 */

interface PlayerRepository  {
    fun findPlayerInfo(uuid: UUID): PlayerInfoTable?
    fun addPlayer(playerTable: PlayerTable, playerInfoTable: PlayerInfoTable): Boolean
}