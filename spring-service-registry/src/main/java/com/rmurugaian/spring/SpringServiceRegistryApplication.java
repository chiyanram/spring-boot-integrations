package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringServiceRegistryApplication {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public CommandLineRunner commandLineRunner() {

        return args -> {

            personRepository.deleteAll();

            Stream.of("Janaki", "Guna", "Ram", "Prakash", "Bharathi")
                    .map(Person::new)
                    .forEach(personRepository::save);

            personRepository
                    .findAll()
                    .forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringServiceRegistryApplication.class, args);
    }
}
