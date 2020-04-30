package com.rmurugaian.spring.controller

import com.rmurugaian.spring.domain.Person
import com.rmurugaian.spring.repository.PersonRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataInitializer {

    private final PersonRepository personRepository

    DataInitializer(final PersonRepository personRepository) {
        this.personRepository = personRepository
    }

    @EventListener(ApplicationReadyEvent.class)
    void initData() {

        Arrays.stream("Ram", "Vijay", "Ganesan")
            .map { it ->
                Person.builder()
                    .name(it)
                    .status("NEW")
                    .build()
            }
            .forEach { personRepository.save(it) }
    }
}

