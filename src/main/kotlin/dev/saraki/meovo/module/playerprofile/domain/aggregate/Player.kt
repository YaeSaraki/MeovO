package dev.saraki.meovo.module.playerprofile.domain.aggregate

import java.time.LocalDateTime
import java.util.UUID

/**
 * 玩家聚合根
 */
abstract class Player(
    open var playerUuid: UUID,
    open var lastLoginTime: LocalDateTime,
) {
}