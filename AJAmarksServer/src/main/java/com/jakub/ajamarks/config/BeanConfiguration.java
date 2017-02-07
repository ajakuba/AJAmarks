package com.jakub.ajamarks.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(basePackages = "com.jakub.ajamarks", excludeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class)})
public class BeanConfiguration {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/message");
        return source;
    }







}
