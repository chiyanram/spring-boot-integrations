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
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String role;

    public User() {
    }

    public User(final String userName, final String password, final String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
