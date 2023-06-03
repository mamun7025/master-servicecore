//package com.mamunrs.servicecore.config.redis;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import java.time.Duration;
//
//
//@Configuration
//@ConditionalOnProperty(name = "redis.enabled", havingValue = "true")
//public class RedisConfig {
//
//    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//
//    @Value("${redis.ttl.hours:10}")
//    private int timeToLive;
//
//    @Value("${redis.ttl.tracker:24}")
//    private int trackerTimeToLive;
//
//    @Value("${redis.ttl.auth:5}")
//    private int authTokenTimeToLive;
//
//    @Value("${redis.host:localhost}")
//    private String redisHost;
//
//    @Value("${redis.port:6379}")
//    private int redisPort;
//
//    @Value("${redis.password:}")
//    private String redisPass;
//
//    @Value("${redis.ssl:false}")
//    private boolean redisSsl;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//        standaloneConfiguration.setPassword(redisPass);
//        // return new LettuceConnectionFactory(standaloneConfiguration);
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
//        if(redisSsl){
//            logger.info("redisSsl set >>>> true");
//            lettuceConnectionFactory.setUseSsl(true);
//        }
//        return lettuceConnectionFactory;
//    }
//
//    @Bean
//    @Primary
//    public RedisCacheManager cacheManager(RedisConnectionFactory factory){
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(factory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofHours(timeToLive))
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())))
//                .build();
//    }
//
//    @Bean("trackerCacheMgr")
//    public RedisCacheManager trackerCacheManager(RedisConnectionFactory factory){
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(factory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofHours(trackerTimeToLive))
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())))
//                .build();
//    }
//
//    @Bean("tokenCacheMgr")
//    public RedisCacheManager authTokenCacheManager(RedisConnectionFactory factory){
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(factory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofHours(authTokenTimeToLive))
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())))
//                .build();
//    }
//
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(){
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
//
//
//}
