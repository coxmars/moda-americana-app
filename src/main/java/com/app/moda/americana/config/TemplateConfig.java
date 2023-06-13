package com.app.moda.americana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class TemplateConfig {
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_0() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("classpath:/templates");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(0);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_1() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/authentication/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(1);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_2() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/binnacle/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(2);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_3() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/category/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(3);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_4() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/contacts/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(4);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_5() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/emails/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(5);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_6() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/products/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(6);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_7() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/providers/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(7);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_8() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/sales/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(8);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_9() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/testimonials/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(9);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_10() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/users/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(10);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver_11() {
        SpringResourceTemplateResolver plantillaResolver = new SpringResourceTemplateResolver();
        plantillaResolver.setPrefix("templates/welcome/");
        plantillaResolver.setSuffix(".html");
        plantillaResolver.setTemplateMode(TemplateMode.HTML);
        plantillaResolver.setCharacterEncoding("UTF-8");
        plantillaResolver.setOrder(11);
        plantillaResolver.setCheckExistence(true);
        return plantillaResolver;
    }
    
}