package com.rmurugaian.spring.activemq.controller;

import com.rmurugaian.spring.activemq.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EventRestController {

    private final JmsTemplate jmsTemplate;
    private final ActiveMQTopic activeMQTopic;

    public EventRestController(final JmsTemplate jmsTemplate, final ActiveMQTopic activeMQTopic) {
        this.jmsTemplate = jmsTemplate;
        this.activeMQTopic = activeMQTopic;
    }

    @PostMapping(path = "/queue")
    public String receiveAndSendToQueue(@RequestBody final Message message) {
        try {
            jmsTemplate.convertAndSend("events", message);
        } catch (final JmsException jmsException) {
            log.error("Unable to send message {} ", jmsException.getMessage(), jmsException);
            return "failure";
        }
        return "success";
    }

    @PostMapping(path = "/topic")
    public String receiveAndSendToTopic(@RequestBody final Message message) {
        try {
            jmsTemplate.convertAndSend(activeMQTopic, message);
        } catch (final JmsException jmsException) {
            log.error("Unable to send message {} ", jmsException.getMessage(), jmsException);
            return "failure";
        }
        return "success";
    }
}