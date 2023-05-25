package com.mamunrs.servicecore.config.datasource;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"com.mamunrs.servicecore.product.repository"}
)
@AllArgsConstructor
@Order(2)
public class ProductDataSourceConfig {


    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "product.datasource")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(
            EntityManagerFactoryBuilder productEntityManagerFactoryBuilder, @Qualifier("productDataSource") DataSource productDataSource) {

        Map<String, String> primaryJpaProperties = new HashMap<>();
        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return productEntityManagerFactoryBuilder
                .dataSource(productDataSource)
                .packages("com.mamunrs.servicecore.product.entity")
                .persistenceUnit("productDS")
                .properties(primaryJpaProperties)
                .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {

        return new JpaTransactionManager(productEntityManagerFactory);
    }


}
