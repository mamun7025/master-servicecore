//package com.mamunrs.servicecore.config.datasource;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "customer2EntityManagerFactory",
//        transactionManagerRef = "customer2TransactionManager",
//        basePackages = {"com.mamunrs.servicecore.app.repository"}
//)
//@AllArgsConstructor
//@Order(2)
//public class DataSourceCustomerConfiguration2 {
//
//    @Primary
//    @Bean(name = "customer2DataSourceProperties")
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties customerDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean(name = "customer2DataSource")
//    @ConfigurationProperties("spring.datasource.configuration")
//    public DataSource customerDataSource(@Qualifier("customer2DataSourceProperties") DataSourceProperties customerDataSourceProperties) {
//        return customerDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Primary
//    @Bean(name = "customer2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
//            EntityManagerFactoryBuilder customerEntityManagerFactoryBuilder, @Qualifier("customer2DataSource") DataSource customerDataSource) {
//
//        Map<String, String> primaryJpaProperties = new HashMap<>();
//        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
//
//        return customerEntityManagerFactoryBuilder
//                .dataSource(customerDataSource)
//                .packages("com.mamunrs.servicecore.app.entity")
//                .persistenceUnit("appDS")
//                .properties(primaryJpaProperties)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "customer2TransactionManager")
//    public PlatformTransactionManager customerTransactionManager(
//            @Qualifier("customer2EntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
//
//        return new JpaTransactionManager(customerEntityManagerFactory);
//    }
//
//}
