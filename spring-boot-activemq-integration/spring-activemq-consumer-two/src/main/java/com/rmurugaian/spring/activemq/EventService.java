package com.rmurugaian.spring.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    public void events(final String message) {
        log.info("Message received {} ", message);
    }

}