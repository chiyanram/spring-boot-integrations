package com.rmurugaian.spring.cache;

import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 * @author rmurugaian 2019-11-14
 */
@SpringBootApplication
@EnableCaching
public class SpringCacheApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

    @Bean
    public GuavaModule guavaModule() {
        return new GuavaModule();
    }

    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }
}
