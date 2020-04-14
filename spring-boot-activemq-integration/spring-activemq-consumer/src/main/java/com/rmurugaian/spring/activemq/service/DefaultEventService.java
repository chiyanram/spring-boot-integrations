package com.rmurugaian.spring.activemq.service;

import com.rmurugaian.spring.activemq.domain.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class DefaultEventService implements EventService {

    private final Map<String, Message> dataStore;

    @Override
    public void events(final Message message) {
        log.info("Message received {} ", message);
        dataStore.put(message.getName(), message);
    }

}