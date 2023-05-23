package com.mamunrs.servicecore.customer.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String code;
    String name;
    String email;
    String mobileNo;

}
