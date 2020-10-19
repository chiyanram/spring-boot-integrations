package com.rmurugaian.spring.cache.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rmurugaian 2019-11-14
 */
@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public static Person create(final String theName) {
        final Person person = new Person();
        person.setName(theName);
        return person;
    }

}
