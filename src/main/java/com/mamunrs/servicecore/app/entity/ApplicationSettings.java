package com.mamunrs.servicecore.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationSettings {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String setting_key;
    String setting_value;

}
