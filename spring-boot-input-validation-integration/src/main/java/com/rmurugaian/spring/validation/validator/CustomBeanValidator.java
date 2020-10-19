package com.rmurugaian.spring.validation.validator;

import com.rmurugaian.spring.validation.*;
import com.rmurugaian.spring.validation.domain.Employee;
import com.rmurugaian.spring.validation.exception.MessageCode;
import com.rmurugaian.spring.validation.exception.MessageParam;
import com.rmurugaian.spring.validation.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomBeanValidator implements Validator {

    private final ApplicationContext applicationContext;

    public CustomBeanValidator(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

        try {
            final List<Field> fieldsToBeValidated = Arrays.stream(target.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CustomValidation.class))
                .collect(Collectors.toList());

            for (final Field field : fieldsToBeValidated) {
                final CustomValidation declaredAnnotation = field.getDeclaredAnnotation(CustomValidation.class);
                final Class<? extends CustomValidator> validator = declaredAnnotation.validator();
                final CustomValidator customValidator = applicationContext.getBean(validator);
                field.setAccessible(true);
                final MessageCode messageCode = customValidator.validate(field.getName(), field.get(target));
                final MessageCode updatedCode = messageCode.toBuilder()
                    .params(Collections.singletonList(new MessageParam(declaredAnnotation.fieldCode(), "")))
                    .build();
                errors.rejectValue(field.getName(), updatedCode.getCode(), new MessageCode[]{updatedCode}, "");
            }

        } catch (final IllegalAccessException e) {
            log.error("exception {}", e.getMessage());
            throw new ValidationException(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.TECHNICAL_PROBLEM);
        }

    }
}
