package dev.saraki.meovo.module.playerprofile.infrastructure.database

import dev.saraki.meovo.module.playerprofile.domain.aggregate.Player
import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerAdvancement
import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerInfo
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
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

    @OneToOne(mappedBy = "playerUuid", cascade = [CascadeType.ALL])
    val playerInfoTable: PlayerInfoTable,

    @OneToMany(mappedBy = "playerUuid", cascade = [CascadeType.ALL], orphanRemoval = true)
    val advancements: MutableSet<PlayerAdvancementTable> = mutableSetOf()
): Serializable, Player(playerUuid, lastLoginTime) {
    override fun getPlayerAdvancements(): Set<PlayerAdvancement> {
        TODO("Not yet implemented")
    }

    override fun getPlayerInfo(): PlayerInfo {
        TODO("Not yet implemented")
    }

}