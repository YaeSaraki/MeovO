package dev.saraki.meovo.module.playerprofile.domain.entity

import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 00:46
 *   @description:
 */
abstract class AdvancementInfo(
    open val id: AdvancementId,
    open val description: String,
) {
}