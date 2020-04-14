package com.rmurugaian.spring.activemq

import com.rmurugaian.spring.activemq.domain.Message
import org.apache.activemq.command.ActiveMQTopic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringActivemqConsumerTwoApplication.class])
@ActiveProfiles("local")
class SpringActivemqConsumerApplicationIT extends Specification {

    @Autowired
    private JmsTemplate jmsTemplate

    @Autowired
    private Map<String, Message> dataStore;


    PollingConditions conditions

    def setup() {
        conditions = new PollingConditions(timeout: 10, initialDelay: 1.5, factor: 1.25)
    }

    def "send message to queue and validate"() {
        when:
        jmsTemplate.convertAndSend("events", Message.builder().name("Ram").build())

        then:
        conditions.eventually {
            assert dataStore["Ram"] == Message.builder().name("Ram").build()
        }
    }

    def "send message to topic and validate"() {
        when:
        jmsTemplate.convertAndSend(new ActiveMQTopic("event-topic"), Message.builder().name("Vijay").build())

        then:
        conditions.eventually {
            assert dataStore["Vijay"] == Message.builder().name("Vijay").build()
        }
    }
}
