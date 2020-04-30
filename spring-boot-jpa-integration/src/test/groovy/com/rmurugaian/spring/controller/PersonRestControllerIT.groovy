package com.rmurugaian.spring.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.rmurugaian.spring.SpringBasicJpaApplication
import com.rmurugaian.spring.domain.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringBasicJpaApplication])
@AutoConfigureMockMvc
@ActiveProfiles("local")
class PersonRestControllerIT extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper


    def "send message to queue api"() {
        expect:
        def response = mockMvc
            .perform(get("/persons"))
            .andExpect(status().isOk())
            .andReturn()
            .response
            .contentAsString

        final List<Person> perosns = objectMapper.readValue(response, new TypeReference<List<Person>>() {})
        perosns.size() == 3
        with(perosns[0]) {
            name == "Ram"
            sequence == 1
            status == "NEW"
        }
        with(perosns[1]) {
            name == "Vijay"
            sequence == 2
            status == "NEW"
        }
        with(perosns[2]) {
            name == "Ganesan"
            sequence == 3
            status == "NEW"
        }
    }

}
