package com.rmurugaian.spring.hibernate.domain;

import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ToString
public class DynamicDataModel {

    private String entityName;
    private Long pkId;
    private LocalDateTime deletedAt;
    private Map<String, Object> properties = new HashMap<>();

    public void put(final String name, final Object value) {
        this.properties.put(name, value);
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> allProps = new HashMap<>();
        allProps.put("pkId", pkId);
        allProps.put("deletedAt", deletedAt);
        allProps.putAll(properties);
        return allProps;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(final String entityName) {
        this.entityName = entityName;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(final Long pkId) {
        this.pkId = pkId;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(final LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, Object> properties) {
        this.properties = properties;
    }
}
