package dev.saraki.meovo.module.playerprofile.infrastructure.database.table

import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerInfo
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 01:59
 *   @description:
 */

@Entity
@Table(name = "player_info")
class PlayerInfoTable (
    @Id
    @Column(nullable = false)
    override val playerUuid: UUID,

    @MapsId                // 从 Player 复用主键值
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_uuid")
    val player: PlayerTable,

    @Embedded
    @Column(nullable = false)
    override val playerBaseInfo: PlayerBaseInfo,

    )  : Serializable, PlayerInfo(playerUuid, playerBaseInfo) {
}