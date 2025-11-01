package dev.saraki.meovo.module.playerprofile.domain.entity

import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerAdvancementId
import java.time.LocalDateTime
import java.util.UUID

/**
*   @author YaeSaraki 
*   @email ikaraswork@iCloud.com
*   @date 2025/10/26 19:39
*   @description: 
*/
abstract class PlayerAdvancement (
    open val id: PlayerAdvancementId,
    open val playerUUid: UUID,
    open val advancementId: AdvancementId,
    open val date: LocalDateTime,
) {
}