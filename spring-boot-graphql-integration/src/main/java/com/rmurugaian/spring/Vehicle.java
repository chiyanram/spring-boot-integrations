package com.rmurugaian.spring;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "model_code", nullable = false)
    private String modelCode;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "launch_date")
    private LocalDate launchDate;

    private transient String formattedDate;

    // Getter and setter
    public String getFormattedDate() {
        return getLaunchDate().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(final String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(final LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public void setFormattedDate(final String formattedDate) {
        this.formattedDate = formattedDate;
    }
}