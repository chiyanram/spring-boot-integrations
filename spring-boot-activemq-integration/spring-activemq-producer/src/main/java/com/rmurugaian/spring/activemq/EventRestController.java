package com.rmurugaian.spring.activemq;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EventRestController {

    private final JmsTemplate jmsTemplate;
    private final ActiveMQTopic activeMQTopic;


    EventRestController(JmsTemplate jmsTemplate, ActiveMQTopic activeMQTopic) {
        this.jmsTemplate = jmsTemplate;
        this.activeMQTopic = activeMQTopic;
    }


    @PostMapping(path = "/events")
    public String events(@RequestBody final String message) {
        jmsTemplate.convertAndSend("events", message);
        return "success";
    }

    @PostMapping(path = "/topic")
    public String topic(@RequestBody final String message) {
        jmsTemplate.convertAndSend(activeMQTopic, message);
        return "success";
    }
}