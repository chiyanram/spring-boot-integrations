package com.rmurugaian.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rmurugaian 2018-07-13
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@ToString(exclude = {"status"})
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    @UpdateTimestamp
    private Date date;

    @Column
    private String status;

}
