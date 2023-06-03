package com.mamunrs.servicecore.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamunrs.servicecore.config.redis.GenericRedisConfig;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@NoArgsConstructor
@ConditionalOnBean({GenericRedisConfig.class})
public class GenRedisService {

    Logger log = LoggerFactory.getLogger(GenRedisService.class);

    @Autowired
    @Qualifier("genRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.write.ttl:60}")
    private long ttl;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get( final String key, final Class<T> clazz){
        Object obj = this.redisTemplate.opsForValue().get(key);
        T data = this.objectMapper.convertValue(obj, clazz);
        return data;
    }

    public <T> T get(final String key, final TypeReference<T> type) {
        Object obj = this.redisTemplate.opsForValue().get(key);
        T data = this.objectMapper.convertValue(obj, type);
        return data;
    }

    public <T> void set(String key, T value){
        log.info("Setting to cache key = {} value = {} ", key, value);
        if(isValueNotEmpty(value)){
            this.redisTemplate.opsForValue().set(key, value);
        }
    }

    public <T> void setWithDefaultTTL(String key, T value){
        log.info("Setting to cache key = {} value = {} ", key, value);
        if(isValueNotEmpty(value)){
            this.redisTemplate.opsForValue().set(key, value);
            this.redisTemplate.expire(key, this.ttl, TimeUnit.MINUTES);
        }
    }

    public <T> void setWithTTL(String key, T value, long ttl){
        log.info("Setting to cache key = {} value = {} ", key, value);
        if(isValueNotEmpty(value)){
            this.redisTemplate.opsForValue().set(key, value);
            this.redisTemplate.expire(key, ttl, TimeUnit.MINUTES);
        }
    }

    public void evictCache(String key){
        if(key != null){
            this.redisTemplate.delete(key);
        }
    }


    private <T> boolean isValueNotEmpty(T value){
        if( value instanceof String){
            return StringUtils.isNoneBlank((String)value);
        }
        if( value instanceof Collection){
            return CollectionUtils.isNotEmpty( (Collection) value );
        }
        return Objects.nonNull(value);
    }

}
