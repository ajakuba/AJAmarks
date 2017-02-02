package com.jakub.ajamarks.config;

import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ja on 11.01.17.
 */

@Configuration
//@PropertySources({
//        @PropertySource("classpath:hsqlfortest.properties"),
//        @PropertySource("classpath:mysqlfortest.properties")
//})
@PropertySource("classpath:mysqlfortest.properties")
@ComponentScan(basePackages = "com.jakub.ajamarks")
@EnableJpaRepositories("com.jakub.ajamarks.repositories")
@EnableTransactionManagement
public class DataBaseForTestConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.jakub.ajamarks.entities");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public static PropertyOverrideConfigurer propertyOverrideConfigurerDev() {
        PropertyOverrideConfigurer overrideConfigurer = new PropertyOverrideConfigurer();
        overrideConfigurer.setLocation(new ClassPathResource("mysqlfortest.properties"));
        return overrideConfigurer;
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }


    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.charSet", "UTF-8");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }


}
