package com.song.core.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * 排序
 * */
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OrderValidator.class)
public @interface Order {
    String message() default "排序类型不支持";

    String[] accepts() default {"desc", "asc"};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
