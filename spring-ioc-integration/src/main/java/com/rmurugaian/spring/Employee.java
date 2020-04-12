package com.rmurugaian.spring;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rmurugaian 2018-05-15
 */
@Value
@Builder
@Slf4j
public class Employee {
    private final String name;

    private Employee(final String name) {
        this.name = name;
        log.info("Employee Constructor {} ", name);
    }
}
