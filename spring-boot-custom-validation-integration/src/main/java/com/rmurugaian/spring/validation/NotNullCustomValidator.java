package com.rmurugaian.spring.validation;

import org.springframework.stereotype.Component;

@Component
public class NotNullCustomValidator implements CustomValidator {
    @Override
    public MessageCode validate(final String field, final Object value) {

        return MessageCode.builder().code(MessageConstants.NON_NULL).field(field).build();
    }
}
