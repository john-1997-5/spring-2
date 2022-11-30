package com.johnson.spring.annotation;

import com.johnson.spring.validations.FieldsValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE}) // la etiqueta se coloca encima de la clase
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
    String message() default "distinct values bitch!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // el código de aquí pa bajo es para cuando se necesitan validar 2 campos diferentes
    String field();

    String fieldMatch();

    /***
     * Intefaz interna que contiene el array con los
     * 2 campos que se tienen que comparar (para ver si son los mismos)
     */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface FieldsList {
        FieldsValueMatch[] value(); // array de FieldsValueMatch
    }

}
