package com.rmurugaian.spring.controller;

import com.rmurugaian.spring.domain.Person;
import com.rmurugaian.spring.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonRestController {

    private final PersonRepository personRepository;

    PersonRestController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> all() {
        return personRepository.findAll();
    }

}