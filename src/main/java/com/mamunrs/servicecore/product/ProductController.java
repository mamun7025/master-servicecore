package com.mamunrs.servicecore.product;

import com.mamunrs.servicecore.product.entity.Product;
import com.mamunrs.servicecore.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct(){
        List<Product> allProduct = productService.getAllProduct();
        return ResponseEntity.ok(allProduct);
    }

    @GetMapping("/cache/all")
    public ResponseEntity<?> getAllProductWithCacheHandler(){
        List<Product> allProduct = productService.fetchAllProduct();
        return ResponseEntity.ok(allProduct);
    }

}
