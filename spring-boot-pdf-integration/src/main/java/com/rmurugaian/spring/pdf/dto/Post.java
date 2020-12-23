package com.rmurugaian.spring.pdf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Post {

    private Global global;

    private Country[] countries;

}