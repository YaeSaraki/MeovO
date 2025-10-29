package dev.saraki.meovo.module.playerprofile.infrastructure.database

import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerAdvancement
import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerAdvancementId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 19:41
 *   @description:
 */
@Entity
@Table(name = "player_advancement")
class PlayerAdvancementTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    override val id: PlayerAdvancementId,

    @Column(nullable = false)
    override val playerId: UUID,

    @Column(nullable = false)
    override val advancementId: AdvancementId,

    @Column(nullable = false)
    override val date: LocalDateTime,

    ) : Serializable, PlayerAdvancement(id, playerId, advancementId, date)
