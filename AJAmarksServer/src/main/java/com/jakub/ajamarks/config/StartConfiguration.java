package com.jakub.ajamarks.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(basePackages = "com.jakub.ajamarks", excludeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class)})
public class StartConfiguration {
}
