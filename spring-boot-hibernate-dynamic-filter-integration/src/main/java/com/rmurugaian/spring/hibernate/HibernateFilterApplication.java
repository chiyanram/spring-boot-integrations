package com.rmurugaian.spring.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HibernateFilterApplication {

    public static void main(final String[] args) {
        SpringApplication.run(HibernateFilterApplication.class, args);
    }

}
