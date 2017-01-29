package com.jakub.ajamarks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by ja on 29.01.17.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jakub.ajamarks")
public class WebAppConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");//folder gdzie poszukiwany jet widok
        bean.setSuffix(".jsp"); //typ pliku jaki poszukujemy
        return bean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
