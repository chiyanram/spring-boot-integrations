package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBasicJpaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBasicJpaApplication.class, args);
    }

}