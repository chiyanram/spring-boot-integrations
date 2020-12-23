package com.rmurugaian.spring.validation.validator;

import com.rmurugaian.spring.validation.exception.MessageCode;

public interface CustomValidator {

    MessageCode validate(String field, Object value);

}
