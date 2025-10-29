package dev.saraki.meovo.module.playerprofile.domain.valueObject

import java.io.Serializable

/**
 *   @author YaeSaraki
 *   @email ikaraswork@iCloud.com
 *   @date 2025/10/26 19:42
 *   @description:
 */
data class PlayerAdvancementId (
    val value: Int
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerAdvancementId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }
}