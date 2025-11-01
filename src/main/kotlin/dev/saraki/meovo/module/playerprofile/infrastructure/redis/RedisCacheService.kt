package dev.saraki.meovo.module.playerprofile.infrastructure.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisCacheService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun <T> get(key: String, type: Class<T>): T? {
        return type.cast(redisTemplate.opsForValue().get(key))
    }

    fun set(key: String, value: Any, expireTime: Long = 3600, timeUnit: TimeUnit = TimeUnit.SECONDS) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit)
    }

    fun delete(key: String) {
        redisTemplate.delete(key)
    }

    fun hasKey(key: String): Boolean {
        return redisTemplate.hasKey(key) ?: false
    }
}