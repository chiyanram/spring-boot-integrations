package com.rmurugaian.spring.cache.service


import com.rmurugaian.spring.cache.SpringCacheApplication
import com.rmurugaian.spring.cache.domain.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringCacheApplication])
class DefaultPersonServiceIT extends Specification {

    @Autowired
    private PersonService personService

    def "verify cache for fetchAll"() {
        when: "check existing persons"
        def persons = personService.fetchAll()

        then: "only three persons should be present"
        persons.size() == 3

        when: "create new person in database"
        personService.save(Person.create("Bharath"))

        then: "fetch all persons"
        def newPerons = personService.fetchAll()
        newPerons.size() == 3
    }
}
