package dev.saraki.meovo.module.playerprofile.infrastructure.database.table

import dev.saraki.meovo.module.playerprofile.domain.aggregate.Player
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "player")
class PlayerTable(
    @Id
    @Column(nullable = false)
    override var playerUuid: UUID,

    @Column(nullable = false)
    override var lastLoginTime: LocalDateTime,

    ): Serializable, Player(playerUuid, lastLoginTime) {
}