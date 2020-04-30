package com.rmurugaian.spring.sequence;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Sequence {

    long initialValue() default 1L;

    long incrementValue() default 1L;

}
