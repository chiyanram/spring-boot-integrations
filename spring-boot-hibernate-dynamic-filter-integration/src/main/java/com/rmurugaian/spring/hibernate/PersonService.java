package com.rmurugaian.spring.hibernate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

    private final PersonRepository repository;

    public PersonService(final PersonRepository repository) {
        this.repository = repository;
    }


    public List<Person> findAll() {
        return repository.findAll(Specification.where(null));
    }


    public Person save(final Person person) {
        return repository.save(person);
    }
}
