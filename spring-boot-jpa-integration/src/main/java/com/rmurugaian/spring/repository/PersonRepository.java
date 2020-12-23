package com.rmurugaian.spring.repository;

import com.rmurugaian.spring.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("InterfaceNeverImplemented")
public interface PersonRepository extends JpaRepository<Person, Long> {
}
