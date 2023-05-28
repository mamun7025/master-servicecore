package com.mamunrs.servicecore.customer;

import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> listData = customerService.getAllCustomer();
        return ResponseEntity.ok(listData);
    }

}
