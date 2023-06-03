package com.mamunrs.servicecore.customer.service;

import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CacheService cacheService;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public List<Customer> fetchAllCustomer(){
        return cacheService.fetchAllCustomer("ALL_CUSTOMER_KEY");
    }

}
