package com.mamunrs.servicecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages="com.mamunrs.servicecore.customer.repository")
public class ServiceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCoreApplication.class, args);
	}

}
