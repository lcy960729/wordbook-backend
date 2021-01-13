package com.example.wordbook.domain.study.exception;

import com.example.wordbook.global.exception.ValidationException;

public class CannotUpdateWordBookNameException extends ValidationException {

    public CannotUpdateWordBookNameException(String message) {
        super(message + " is Wrong Input. Name cannot consisting only numeric");
    }
}
