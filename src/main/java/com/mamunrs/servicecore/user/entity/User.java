package com.mamunrs.servicecore.user.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String username;
    String password;

    String fullName;
    String email;
    String mobileNo;
    String address;

}
