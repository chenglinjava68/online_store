package com.yuan.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author joryun ON 2017/10/22.
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redis")
    RedisTemplate setRedis(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> t = new RedisTemplate<>();
        t.setConnectionFactory(redisConnectionFactory);
        t.setKeySerializer(t.getStringSerializer());
        t.setHashKeySerializer(t.getStringSerializer());
        t.setDefaultSerializer(jackson2JsonRedisSerializer);
        t.afterPropertiesSet();
        return t;
    }

    /**
     * 默认缓存3天
     */
    @Bean
    @SuppressWarnings("unchecked")
    RedisCacheManager setRedisCacheManager(@Qualifier("redis") RedisTemplate redisTemplate) {
        final RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(TimeUnit.DAYS.toSeconds(3));
        redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix());
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setExpires(new HashMap<String, Long>() {
            {
                this.put("cms:company:statistic", TimeUnit.MINUTES.toSeconds(10));
                this.put("cms:product:statistic", TimeUnit.HOURS.toSeconds(12));
            }
        });
        return redisCacheManager;
    }

}
