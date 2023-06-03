package com.mamunrs.servicecore.product.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mamunrs.servicecore.cache.CacheHandler;
import com.mamunrs.servicecore.product.entity.Product;
import com.mamunrs.servicecore.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CacheHandler<List<Product>> cacheHandler;

    private static final TypeReference<List<Product>> PRODUCT_LIST_DTO_TYPE = new TypeReference<List<Product>>() {
    };
    private static final String ALL_PRODUCT_CACHE_KEY = "ALL_PRODUCT_KEY";

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> fetchAllProduct(){
        log.info("Fetch all products");
        return cacheHandler.getValue(this::getAllProductFromDB, ALL_PRODUCT_CACHE_KEY, PRODUCT_LIST_DTO_TYPE);
    }
    public List<Product> getAllProductFromDB(){
        List<Product> allProducts =  productRepository.findAll();
        return allProducts;
    }

}
