package dev.saraki.meovo.module.playerprofile.infrastructure.database

import dev.saraki.meovo.module.playerprofile.domain.entity.PlayerInfo
import dev.saraki.meovo.module.playerprofile.domain.valueObject.PlayerBaseInfo
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import java.time.LocalDateTime
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

    @Embedded
    @Column(nullable = false)
    override val playerBaseInfo: PlayerBaseInfo,

)  : Serializable, PlayerInfo(playerUuid, playerBaseInfo) {

}