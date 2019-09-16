package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class SpringBasicJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicJpaApplication.class, args);
    }

}

@RestController
class PersonRestController {

    private final PersonRepository personRepository;

    PersonRestController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> all() {
        return personRepository.findAll();
    }

}