package com.mamunrs.servicecore.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@PropertySource({ "classpath:multiple-db.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "h2DbEntityManagerFactory",
        transactionManagerRef = "h2DbTransactionManager",
        basePackages = {"com.mamunrs.servicecore.h2entities.repository"}
)
public class H2DataSourceConfig {

    @Autowired
    private Environment env;


    @Bean(name = "h2DataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("h2.datasource.driverClassName"))
                .url(env.getProperty("h2.datasource.url"))
                .username(env.getProperty("h2.datasource.username"))
                .password(env.getProperty("h2.datasource.password"))
                .build();
    }


    @Bean(name = "h2DbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2DbEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("h2DataSource") DataSource dataSource) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.mamunrs.servicecore.h2entities.entity")
                        .persistenceUnit("h2Db")
                        .build();
    }


    @Bean(name = "h2DbTransactionManager")
    public PlatformTransactionManager h2DbTransactionManager(@Qualifier("h2DbEntityManagerFactory") EntityManagerFactory h2DbEntityManagerFactory) {
        return new JpaTransactionManager(h2DbEntityManagerFactory);
    }

}
