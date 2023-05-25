package com.mamunrs.servicecore.product.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Product {

    @Id
    Long id;
    String code;
    String name;
    String productCode;

}
