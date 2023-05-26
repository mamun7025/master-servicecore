package com.mamunrs.servicecore.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager",
        basePackages = {"com.mamunrs.servicecore.app.repository"}
)
@AllArgsConstructor
@Order(1)
public class AppMainDataSourceConfig {

    @Primary
    @Bean(name = "appDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "appDataSource")
    @ConfigurationProperties("spring.datasource.configuration")
    public DataSource appDataSource(@Qualifier("appDataSourceProperties") DataSourceProperties appDataSourceProperties) {
        return appDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            EntityManagerFactoryBuilder appEntityManagerFactoryBuilder, @Qualifier("appDataSource") DataSource appDataSource) {

        Map<String, String> primaryJpaProperties = new HashMap<>();
        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "update");

//        primaryJpaProperties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        primaryJpaProperties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        primaryJpaProperties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());

        return appEntityManagerFactoryBuilder
                .dataSource(appDataSource)
                .packages("com.mamunrs.servicecore.app.entity")
                .persistenceUnit("appDS")
                .properties(primaryJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory) {

        return new JpaTransactionManager(appEntityManagerFactory);
    }


}
