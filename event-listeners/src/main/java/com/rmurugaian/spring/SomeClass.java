package com.rmurugaian.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SomeClass {

    @EventListener
    public void update(final PurposeEvent purposeEvent) {
        log.info("CALLED {} ::: ", purposeEvent);
    }
}