package com.rmurugaian.spring.activemq.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.rmurugaian.spring.activemq.ActiveMqProducerApplication
import com.rmurugaian.spring.activemq.domain.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ActiveMqProducerApplication])
@AutoConfigureMockMvc
@ActiveProfiles("local")
class EventControllerIT extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private JmsTemplate jmsTemplate

    @Autowired
    private ObjectMapper objectMapper

    PollingConditions conditions

    def setup() {
        conditions = new PollingConditions(timeout: 10, initialDelay: 1.5, factor: 1.25)
    }

    def "send message to queue api"() {
        given:
        def inputMessage = objectMapper.writeValueAsString(Message.builder().name("Ram").build())

        expect:
        mockMvc
            .perform(
                post("/queue")
                    .content(inputMessage)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("success"))

        conditions.eventually {
            def message = jmsTemplate.receiveAndConvert("events")
            assert message instanceof Message
            assert message == Message.builder().name("Ram").build()
        }
    }

}
