package com.rmurugaian.spring.config;

import com.rmurugaian.spring.listener.CreatedTimeInterceptor;
import com.rmurugaian.spring.listener.EventListenerIntegrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix="security.datasource")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }

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
