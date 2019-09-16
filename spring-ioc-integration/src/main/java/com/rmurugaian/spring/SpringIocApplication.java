package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author rmurugaian 2018-05-15
 */
@SpringBootApplication
public class SpringIocApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);
    }

    @Bean
    @Scope("prototype")
    public Employee employee() {

        return Employee.builder().name("Ram").build();
    }
}
