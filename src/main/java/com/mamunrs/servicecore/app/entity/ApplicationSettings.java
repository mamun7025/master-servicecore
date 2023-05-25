package com.mamunrs.servicecore.app.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ApplicationSettings {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String settingKey;
    String settingValue;

}
