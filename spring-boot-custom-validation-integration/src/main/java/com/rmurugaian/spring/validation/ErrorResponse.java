package com.rmurugaian.spring.validation;

import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ErrorResponse {

    private List<MessageCode> errors;

    public static ErrorResponse errors(final List<MessageCode> theErrors) {
        return new ErrorResponse(theErrors);
    }

    public static ErrorResponse error(final MessageCode error) {
        return new ErrorResponse(Collections.singletonList(error));
    }
}
