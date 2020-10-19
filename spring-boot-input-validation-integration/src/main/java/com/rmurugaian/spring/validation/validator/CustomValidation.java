package com.rmurugaian.spring.validation.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidation {

    Class<? extends CustomValidator> validator();

    String fieldCode();

}
