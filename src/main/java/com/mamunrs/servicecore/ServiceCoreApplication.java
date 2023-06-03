package com.mamunrs.servicecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ServiceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCoreApplication.class, args);
	}

}
