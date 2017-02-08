package com.jakub.ajamarks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataBaseConfiguration.class})
@TestPropertySource("/jpafortestsproperties.properties")
public class DataBaseConfigurationTest {

    @Autowired
    Properties jpaProperties;

    @Autowired
    DriverManagerDataSource driverManagerDataSource;

    @Test
    public void jpaPropertyTest(){
        assertEquals("initial_data.sql", jpaProperties.get("hibernate.hbm2ddl.import_files"));
        assertEquals("create", jpaProperties.get("hibernate.hbm2ddl.auto"));
        assertEquals("true", jpaProperties.get("hibernate.show_sql"));
        assertEquals("false", jpaProperties.get("hibernate.format_sql"));
        assertEquals("true", jpaProperties.get("hibernate.connection.useUnicode"));
        assertEquals("UTF-8", jpaProperties.get("hibernate.connection.characterEncoding"));
        assertEquals("UTF-8", jpaProperties.get("hibernate.connection.charSet"));
    }

    @Test
    public void dataSourceTest(){
        assertEquals(driverManagerDataSource.getUrl(), "jdbc:mysql://localhost:3306/marks");
        assertEquals(driverManagerDataSource.getUsername(), "marks");
        assertEquals(driverManagerDataSource.getPassword(), "marks");
    }


}