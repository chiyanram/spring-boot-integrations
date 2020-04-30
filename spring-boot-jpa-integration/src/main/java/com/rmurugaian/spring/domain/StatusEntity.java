package com.rmurugaian.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class StatusEntity extends AuditEntity {
    @Column(name = "status")
    private String status;
}
