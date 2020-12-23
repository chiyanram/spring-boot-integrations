package com.rmurugaian.spring.domain;

import com.rmurugaian.spring.sequence.Sequence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Person extends StatusEntity {

    @Column(name = "sequence", updatable = false)
    @Sequence
    private Long sequence;
    private String name;

    public static Person create(final String theName) {
        final Person person = new Person();
        person.setName(theName);
        return person;
    }

}