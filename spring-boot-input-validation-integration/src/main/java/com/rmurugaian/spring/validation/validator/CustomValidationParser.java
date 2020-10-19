package com.rmurugaian.spring.validation.validator;

import com.rmurugaian.spring.validation.exception.MessageCode;
import com.rmurugaian.spring.validation.MessageConstants;
import lombok.experimental.UtilityClass;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CustomValidationParser {

    public List<MessageCode> getValidationErrors(final MethodArgumentNotValidException exception) {
        //TODO: define validation framework and parse exception accordingly
        return exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> {
                final Object[] arguments = fieldError.getArguments();
                if (arguments != null) {
                    return Arrays.stream(arguments)
                        .filter(it -> it instanceof MessageCode)
                        .map(MessageCode.class::cast)
                        .findAny()
                        .orElseGet(() -> MessageCode.error(MessageConstants.TECHNICAL_PROBLEM));
                }
                return MessageCode.error(MessageConstants.TECHNICAL_PROBLEM);
            })
            .collect(Collectors.toList());
    }
}
