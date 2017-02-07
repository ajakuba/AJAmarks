package com.jakub.ajamarks.config;

import com.jakub.ajamarks.services.showdataservices.SubjectService;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(basePackages = "com.jakub.ajamarks", excludeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class)})
@PropertySource("classpath:mysqlfortest.properties")
public class WebAppTestConfigurationContext extends WebMvcConfigurerAdapter {


    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");//folder gdzie poszukiwany jet widok
        resolver.setSuffix(".jsp"); //typ pliku jaki poszukujemy
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//
// @Bean
//    SubjectService mockSubjectService(){
//        return mock(SubjectService.class);
//    }
}
