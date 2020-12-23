package com.rmurugaian.spring.cache;

import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.rmurugaian.spring.cache.domain.Person;
import com.rmurugaian.spring.cache.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

/**
 * @author rmurugaian 2019-11-14
 */
@SpringBootApplication
@EnableCaching
@Slf4j
public class SpringCacheApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

    @Bean
    public GuavaModule guavaModule() {
        return new GuavaModule();
    }

    @Bean
    public CommandLineRunner commandLineRunner(final PersonRepo personRepo) {

        return args -> Stream.of("Ram", "Vijay", "tamil")
            .map(Person::create)
            .map(personRepo::save)
            .forEach(it -> log.info("Person Saved {} ", it));
    }
}
