package com.rmurugaian.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rmurugaian 2018-04-06
 */
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String deptName;

    public Student() {
    }

    public Student(final String name, final String deptName) {
        this.name = name;
        this.deptName = deptName;
    }
}
