package com.mamunrs.servicecore.customer.service;

import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

}
