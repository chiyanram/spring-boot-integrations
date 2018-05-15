package com.rmurugaian.spring;

import lombok.Builder;
import lombok.Value;

/**
 * @author rmurugaian 2018-05-15
 */
@Value
@Builder
public class Employee {
    private final String name;

    private Employee(final String name) {
        this.name = name;
    }
}
