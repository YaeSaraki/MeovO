package dev.saraki.meovo.module.playerprofile.infrastructure.redis.repository.impl

import dev.saraki.meovo.module.playerprofile.domain.repository.PlayerRepository
import dev.saraki.meovo.module.playerprofile.infrastructure.database.repository.impl.PlayerRepositoryImpl
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerInfoTable
import dev.saraki.meovo.module.playerprofile.infrastructure.database.table.PlayerTable
import dev.saraki.meovo.module.playerprofile.infrastructure.redis.RedisCacheService
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.concurrent.TimeUnit

@Primary
@Repository
class CachedPlayerRepositoryImpl(
    private val playerRepositoryImpl: PlayerRepositoryImpl,
    private val redisCacheService: RedisCacheService
) : PlayerRepository {
    private companion object {
        const val PLAYER_INFO_CACHE_PREFIX = "player:info:"
    }

    override fun findPlayerInfo(uuid: UUID): PlayerInfoTable? {
        val cacheKey = PLAYER_INFO_CACHE_PREFIX + uuid.toString()

        // 先从缓存获取
        var playerInfo = redisCacheService.get(cacheKey, PlayerInfoTable::class.java)
        if (playerInfo != null) {
            return playerInfo
        }

        // 缓存未命中，委托给PlayerRepositoryImpl从数据库获取
        playerInfo = playerRepositoryImpl.findPlayerInfo(uuid)

        // 将结果存入缓存，设置过期时间
        if (playerInfo != null) {
            redisCacheService.set(cacheKey, playerInfo, 30, TimeUnit.MINUTES)
        }

        return playerInfo
    }

    override fun addPlayer(
        playerTable: PlayerTable,
        playerInfoTable: PlayerInfoTable
    ): Boolean {
        // 委托给PlayerRepositoryImpl执行数据库操作
        val result = playerRepositoryImpl.addPlayer(playerTable, playerInfoTable)

        // 清理可能存在的旧缓存
        val cacheKey = PLAYER_INFO_CACHE_PREFIX + playerTable.playerUuid.toString()
        redisCacheService.delete(cacheKey)

        return result
    }
}