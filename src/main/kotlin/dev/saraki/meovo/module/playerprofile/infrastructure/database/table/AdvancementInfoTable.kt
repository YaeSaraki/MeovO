package dev.saraki.meovo.module.playerprofile.infrastructure.database.table

import dev.saraki.meovo.module.playerprofile.domain.entity.AdvancementInfo
import dev.saraki.meovo.module.playerprofile.domain.valueObject.AdvancementId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 19:04
 *   @description:
 */

@Entity
@Table(name = "advancement_info")
class AdvancementInfoTable (
    @Id
    @Column(nullable = false)
    override val id: AdvancementId,

    @Column(nullable = false)
    override val description: String,

    ) : Serializable, AdvancementInfo(id, description)