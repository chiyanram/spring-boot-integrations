package com.rmurugaian.spring.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public MvcConfig(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Validator getValidator() {
        return customBeanValidator();
    }

    @Bean
    public CustomBeanValidator customBeanValidator() {
        return new CustomBeanValidator(applicationContext);
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}