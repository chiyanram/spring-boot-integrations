package com.rmurugaian.spring.validation;

public interface CustomValidator {

    MessageCode validate(String field, Object value);

}
