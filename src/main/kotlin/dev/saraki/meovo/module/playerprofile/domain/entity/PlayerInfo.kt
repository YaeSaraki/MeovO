package dev.saraki.meovo.module.playerprofile.domain.entity

import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 19:29
 *   @description:
 */
abstract class PlayerInfo (
    open val playerUuid: UUID,
    open val playerBaseInfo: PlayerBaseInfo,
) {
}