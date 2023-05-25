package com.mamunrs.servicecore.customer;

import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> getData(){
        List<Customer> listData = customerRepository.findAll();
        return ResponseEntity.ok(listData);
    }

}
