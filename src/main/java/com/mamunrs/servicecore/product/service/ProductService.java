package com.mamunrs.servicecore.product.service;

import com.mamunrs.servicecore.product.entity.Product;
import com.mamunrs.servicecore.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

}
