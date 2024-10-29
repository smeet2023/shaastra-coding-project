package com.shaastra.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {TpIdValidator.class} )
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTpId {
    String message() default "!! -> tp_id must match the pattern: STARTYEAR-BRANCHDIVISIONROLLNO-ENDYEAR";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

