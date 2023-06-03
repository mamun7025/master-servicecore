package com.mamunrs.servicecore.config.redis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import java.time.Duration;

@Configuration
@NoArgsConstructor
@ConditionalOnProperty(
        name = {"redis.enableRedisCache"},
        havingValue = "true",
        matchIfMissing = false
)
public class GenRedisConfig {

    private final Logger logger = LoggerFactory.getLogger(GenRedisConfig.class);

    @Value("${redis.host:localhost}")
    private String redisHost;

    @Value("${redis.port:6379}")
    private int redisPort;

    @Value("${redis.password:}")
    private String redisPass;

    @Value("${redis.ssl:false}")
    private boolean redisSsl;

    @Value("${redis.timeout:5000}")
    private Integer timeout;

    @Primary
    @Bean({"genJedisConnectionFactory"})
    JedisConnectionFactory genericJedisConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(this.buildPoolConfig());
        connectionFactory.setHostName(this.redisHost);
        connectionFactory.setPort(this.redisPort);
        connectionFactory.setUsePool(true);
        connectionFactory.setUseSsl(this.redisSsl);
        connectionFactory.setTimeout(this.timeout);
        if(this.redisPass != null){
            connectionFactory.setPassword(this.redisPass);
        }
        logger.info("Redis connection pool is set");
        return  connectionFactory;
    }

    @Primary
    @Bean({"genRedisTemplate"})
    RedisTemplate<String, Object> genericRedisTemplate(@Qualifier("genJedisConnectionFactory") JedisConnectionFactory genericJedisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(genericJedisConnectionFactory);
        // redisTemplate.setConnectionFactory(this.genericJedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }


    private JedisPoolConfig buildPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60L));
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30L));
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }


}
