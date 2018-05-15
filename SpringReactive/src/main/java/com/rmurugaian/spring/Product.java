package com.rmurugaian.spring;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rmurugaian 2018-04-25
 */
@Document
@Data
public class Product {

    @Id
    private Long id;
    private String name;
    private String desc;

    public Product() {
    }

    public Product(final Long id, final String name, final String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
