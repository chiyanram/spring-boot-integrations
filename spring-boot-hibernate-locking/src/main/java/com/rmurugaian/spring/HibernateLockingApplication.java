package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author rmurugaian 2018-07-13
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class HibernateLockingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(HibernateLockingApplication.class, args);
    }

}
