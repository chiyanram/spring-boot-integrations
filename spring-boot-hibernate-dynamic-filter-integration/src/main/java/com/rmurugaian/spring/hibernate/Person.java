package com.rmurugaian.spring.hibernate;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@FilterDef(name = "mFilter", parameters = {
        @ParamDef(name = "tenantId", type = "long")
})
@Filter(name = "mFilter", condition = "tenant_id = :tenantId")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long tenantId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(final Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", name='" + name + '\'' +
                '}';
    }
}
