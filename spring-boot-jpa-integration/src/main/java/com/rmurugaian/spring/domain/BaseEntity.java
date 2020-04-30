package com.rmurugaian.spring.domain;

import com.rmurugaian.spring.sequence.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GenericGenerator(name = IdGenerator.ID_TABLE, strategy = IdGenerator.ID_GENERATOR)
    @GeneratedValue(generator = IdGenerator.ID_TABLE)
    @Column(name = "pk_id")
    private Long id;

}
