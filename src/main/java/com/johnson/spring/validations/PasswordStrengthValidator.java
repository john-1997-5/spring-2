package com.johnson.spring.validations;

import com.johnson.spring.annotation.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    // la contraseña introducida será válida si no es nula y si no está entre las
    // contraseñas consideradas como débiles
    @Override
    public boolean isValid(String introPassword, ConstraintValidatorContext context) {
        return introPassword != null && !weakPasswords.contains(introPassword);
    }
}
