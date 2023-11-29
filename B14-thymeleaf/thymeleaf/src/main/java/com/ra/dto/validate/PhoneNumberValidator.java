package com.ra.dto.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// Phone là kiểu dữ liệu gì thì  biểu thức kim cương a để kiểu dữ liệu đó
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint,String> {
    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }
}
