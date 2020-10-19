package com.rmurugaian.spring.validation.config;

import com.rmurugaian.spring.validation.validator.CustomBeanValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ValidationConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public ValidationConfig(final ApplicationContext applicationContext) {
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

}