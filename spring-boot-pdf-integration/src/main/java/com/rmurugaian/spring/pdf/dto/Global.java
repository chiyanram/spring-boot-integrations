package com.rmurugaian.spring.pdf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Global {

    private Long newconfirmed;
    private Long totalconfirmed;
    private Long newdeaths;
    private Long totaldeaths;
    private Long newrecovered;
    private Long totalrecovered;
}