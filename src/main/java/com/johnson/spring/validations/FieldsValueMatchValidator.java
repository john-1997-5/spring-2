package com.johnson.spring.validations;

import com.johnson.spring.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String field1;
    private String field2;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field1 = constraintAnnotation.field();
        this.field2 = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object field1Value = new BeanWrapperImpl(value).getPropertyValue(field1);
        Object field2Value = new BeanWrapperImpl(value).getPropertyValue(field2);
        return field1Value != null
                && (field1Value.equals(field2Value)
                || field1Value.toString().startsWith("$2a"));
    }
}
