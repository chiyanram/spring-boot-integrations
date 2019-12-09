package com.rmurugaian.spring.cache;

import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.rmurugaian.spring.cache.domain.Person;
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

    @Bean
    public CommandLineRunner commandLineRunner(final PersonRepo personRepo) {

        return args -> Stream.of("Ram", "Vijay", "tamil").map(s -> {
            final Person person = new Person();
            person.setName(s);
            return person;
        }).forEach(personRepo::save);
    }
}
