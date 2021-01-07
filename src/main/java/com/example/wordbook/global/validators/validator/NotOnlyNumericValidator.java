package com.example.wordbook.global.validators.validator;

import com.example.wordbook.domain.userwordbook.controller.UserWordBookController;
import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotOnlyNumericValidator implements ConstraintValidator<NotOnlyNumeric, String> {
    private static final Logger logger = LoggerFactory.getLogger(NotOnlyNumericValidator.class);
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (value == null)
            return false;

        boolean isOnlyNumeric = value.matches("^[0-9]+$");

        if (isOnlyNumeric){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("단어장 이름은 문자+숫자로 입력 해주세요").addConstraintViolation();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(NotOnlyNumeric constraintAnnotation) {
    }


}
