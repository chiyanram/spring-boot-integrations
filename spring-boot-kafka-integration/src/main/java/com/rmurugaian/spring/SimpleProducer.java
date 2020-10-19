package com.rmurugaian.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SimpleProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SimpleProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final String message) {
        log.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send("simple-message", message);
    }
}
