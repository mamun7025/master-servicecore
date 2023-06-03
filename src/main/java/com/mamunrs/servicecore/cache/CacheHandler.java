package com.mamunrs.servicecore.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheHandler<T> {

    @Autowired(required = false)
    private GenRedisService redisService;


    public T getValue(final Supplier<T> supplier, final String cacheKey, final TypeReference<T> type){
        T cache = redisService.get(cacheKey, type);
        if(null != cache){
            log.info("Cache HIT for key = {}", cacheKey);
            return cache;
        }
        log.info("Cache MISS for key = {}", cacheKey);
        T response = supplier.get();
        if(response != null){
            redisService.setWithDefaultTTL(cacheKey, response);
        }
        return response;
    }


    public T getValue(final Supplier<T> supplier, final String cacheKey, final Class<T> clazz){
        T cache = redisService.get(cacheKey, clazz);
        if(null != cache){
            log.info("Cache HIT for key = {}", cacheKey);
            return cache;
        }
        log.info("Cache MISS for key = {}", cacheKey);
        T response = supplier.get();
        if(response != null){
            redisService.setWithDefaultTTL(cacheKey, response);
        }
        return response;
    }

    public T getValue(final String cacheKey, final Class<T> clazz){
        return redisService.get(cacheKey, clazz);
    }


}
