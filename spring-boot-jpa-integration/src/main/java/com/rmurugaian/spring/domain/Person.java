package com.rmurugaian.spring.domain;

import com.rmurugaian.spring.sequence.IdGenerator;
import com.rmurugaian.spring.sequence.Sequence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GenericGenerator(name = IdGenerator.ID_TABLE, strategy = IdGenerator.ID_GENERATOR)
    @GeneratedValue(generator = IdGenerator.ID_TABLE)
    @Column(name = "pk_id")
    private Long id;
    @Column(name = "sequence", updatable = false)
    @Sequence
    private Long sequence;
    private String name;
    private String status;
}