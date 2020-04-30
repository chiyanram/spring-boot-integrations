package com.rmurugaian.spring.config;

import com.rmurugaian.spring.listener.CreatedTimeInterceptor;
import com.rmurugaian.spring.listener.EventListenerIntegrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return properties -> {
            properties.put(
                "hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(EventListenerIntegrator.INSTANCE));
            properties.put("hibernate.ejb.interceptor", CreatedTimeInterceptor.INSTANCE);
        };
    }
}
