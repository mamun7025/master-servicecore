//package com.mamunrs.servicecore.config.datasource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "usrEntityManagerFactory",
//        transactionManagerRef = "userTransactionManager",
//        basePackages = {"com.mamunrs.servicecore.db1package.repository"}
//)
//public class DataSourceUserConfiguration {
//
//    @Autowired
//    private Environment env;
//
//    @Primary
//    @Bean(name = "usrDataSource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName(env.getProperty("spring.datasource-db2.driver-class-name"))
//                .url(env.getProperty("spring.datasource-db2.url"))
//                .username(env.getProperty("spring.datasource-db2.username"))
//                .password(env.getProperty("spring.datasource-db2.password"))
//                .build();
//    }
//
//
//    @Primary
//    @Bean(name = "usrEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("usrDataSource") DataSource dataSource) {
//        return
//                builder
//                        .dataSource(dataSource)
//                        .packages("com.mamunrs.servicecore.db1package.entity")
//                        .persistenceUnit("db1Source")
//                        .build();
//    }
//
//    @Primary
//    @Bean(name = "userTransactionManager")
//    public PlatformTransactionManager userTransactionManager(@Qualifier("usrEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
//        return new JpaTransactionManager(userEntityManagerFactory);
//    }
//
//
//}
