package com.rmurugaian.spring.validation.validator;

import com.rmurugaian.spring.validation.exception.MessageCode;
import com.rmurugaian.spring.validation.MessageConstants;
import org.springframework.stereotype.Component;

@Component
public class NotNullCustomValidator implements CustomValidator {
    @Override
    public MessageCode validate(final String field, final Object value) {

        return MessageCode.builder().code(MessageConstants.NON_NULL).field(field).build();
    }
}
