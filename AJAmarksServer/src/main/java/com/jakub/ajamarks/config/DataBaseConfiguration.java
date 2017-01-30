package com.jakub.ajamarks.config;

import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ja on 11.01.17.
 */

@Configuration
@EnableJpaRepositories("com.jakub.ajamarks")
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:mysql.properties"),
     //   @PropertySource("classpath:hsql.properties")
})
public class DataBaseConfiguration {

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
        properties.setProperty("hibernate.hbm2ddl.import_files", "initial_data.sql");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.charSet", "UTF-8");
        return properties;
    }

    @Bean
    public static PropertyOverrideConfigurer propertyOverrideConfigurerDev() {
        PropertyOverrideConfigurer overrideConfigurer = new PropertyOverrideConfigurer();
        overrideConfigurer.setLocation(new ClassPathResource("mysql.properties"));
        return overrideConfigurer;
    }

    @Bean
    @Profile("test")
    public static PropertyOverrideConfigurer propertyOverrideConfigurerTest() {
        PropertyOverrideConfigurer overrideConfigurer = new PropertyOverrideConfigurer();
        overrideConfigurer.setLocation(new ClassPathResource("hsql.properties"));
        return overrideConfigurer;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }


}
