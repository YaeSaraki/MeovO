package dev.saraki.meovo.module.playerprofile.domain.aggregate

import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerAdvancement
import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerInfo
import java.time.LocalDateTime
import java.util.UUID

/**
 * 玩家聚合根
 */
abstract class Player(
    open var playerUuid: UUID,
    open var lastLoginTime: LocalDateTime,
) {
    abstract fun getPlayerAdvancements(): Set<PlayerAdvancement>
    abstract fun getPlayerInfo(): PlayerInfo
}