package com.johnson.spring.annotation;

import com.johnson.spring.validations.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "la contraseña es débil sasuke";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
