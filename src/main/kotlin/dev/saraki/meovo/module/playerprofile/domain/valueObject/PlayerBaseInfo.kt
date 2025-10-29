package dev.saraki.meovo.module.playerprofile.domain.valueObject

import java.time.LocalTime

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 00:43
 *   @description:
 */
data class PlayerBaseInfo(
    val name: String,
    val firstLoginTime: LocalTime,
) {

}