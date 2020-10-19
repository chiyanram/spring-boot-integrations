package com.rmurugaian.spring.validation.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rmurugaian.spring.validation.validator.CustomValidation;
import com.rmurugaian.spring.validation.validator.NotNullCustomValidator;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Employee.EmployeeBuilder.class)
public class Employee {

    @CustomValidation(validator = NotNullCustomValidator.class, fieldCode = "EMP_NAME")
    private String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EmployeeBuilder {

    }

}
