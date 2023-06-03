package com.mamunrs.servicecore.customer.service;

import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CacheService {

    @Autowired
    CustomerRepository customerRepository;


    private static final String CUSTOMER_CACHE_SESSION = "customer-cache-session";

//    @Cacheable(value = CUSTOMER_CACHE_SESSION, cacheManager = "trackerCacheMgr", unless = "#result.size() == 0", key = "#cacheKey")
    @Cacheable(value = CUSTOMER_CACHE_SESSION, cacheManager = "trackerCacheMgr")
    public List<Customer> fetchAllCustomer(String cacheKey){
        log.info("Caching fetchAllCustomer for key: {}", cacheKey);
        return customerRepository.findAll();
    }

}
