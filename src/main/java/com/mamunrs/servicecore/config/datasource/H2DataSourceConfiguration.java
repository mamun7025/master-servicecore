//package com.mamunrs.servicecore.config.datasource;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import static java.util.Collections.singletonMap;
//
//@Configuration
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "secondEntityManagerFactory",
//        transactionManagerRef = "secondTransactionManager",
//        basePackages = "com.mamunrs.servicecore.db2package"
//)
//@EnableTransactionManagement
//public class H2DataSourceConfiguration {
//
//    @Bean(name = "secondEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(final EntityManagerFactoryBuilder builder,
//                                                                             final @Qualifier("second-db") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.marcosbarbero.wd.pcf.multidatasources.second.domain")
//                .persistenceUnit("secondDb")
//                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop"))
//                .build();
//    }
//
//    @Bean(name = "secondTransactionManager")
//    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondEntityManagerFactory")
//                                                               EntityManagerFactory secondEntityManagerFactory) {
//        return new JpaTransactionManager(secondEntityManagerFactory);
//    }
//
//}
