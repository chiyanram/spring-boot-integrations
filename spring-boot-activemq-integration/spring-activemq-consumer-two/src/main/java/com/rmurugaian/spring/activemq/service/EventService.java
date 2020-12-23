package com.rmurugaian.spring.activemq.service;

import com.rmurugaian.spring.activemq.domain.Message;

public interface EventService {
    void events(Message message);
}
