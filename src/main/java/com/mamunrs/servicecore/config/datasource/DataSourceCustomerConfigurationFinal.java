package com.mamunrs.servicecore.config.datasource;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager",
        basePackages = {"com.mamunrs.servicecore.customer.repository"}
)
//@AllArgsConstructor
//@Order(2)
public class DataSourceCustomerConfigurationFinal {

//    @Primary
//    @Bean(name = "customerDataSourceProperties")
//    @ConfigurationProperties("spring.datasource-customer")
//    public DataSourceProperties customerDataSourceProperties() {
//        return new DataSourceProperties();
//    }

//    @Primary
//    @Bean(name = "customerDataSource")
//    @ConfigurationProperties("spring.datasource-customer.configuration")
//    public DataSource customerDataSource(@Qualifier("customerDataSourceProperties") DataSourceProperties customerDataSourceProperties) {
//        return customerDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }

    @Autowired
    private Environment env;

    @Bean(name = "customerDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource-customer.driver-class-name"))
                .url(env.getProperty("spring.datasource-customer.url"))
                .username(env.getProperty("spring.datasource-customer.username"))
                .password(env.getProperty("spring.datasource-customer.password"))
                .build();
    }


    //    @Primary
    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
            EntityManagerFactoryBuilder customerEntityManagerFactoryBuilder, @Qualifier("customerDataSource") DataSource customerDataSource) {

        Map<String, String> primaryJpaProperties = new HashMap<>();
        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return customerEntityManagerFactoryBuilder
                .dataSource(customerDataSource)
                .packages("com.mamunrs.servicecore.customer.entity")
                .persistenceUnit("customerDS")
                .properties(primaryJpaProperties)
                .build();
    }

//    @Primary
    @Bean(name = "customerTransactionManager")
    public PlatformTransactionManager customerTransactionManager(
            @Qualifier("customerEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {

        return new JpaTransactionManager(customerEntityManagerFactory);
    }

}
