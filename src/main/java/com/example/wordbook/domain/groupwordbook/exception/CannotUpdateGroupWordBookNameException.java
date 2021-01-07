package com.example.wordbook.domain.groupwordbook.exception;

import com.example.wordbook.global.exception.ValidationException;

public class CannotUpdateGroupWordBookNameException extends ValidationException {

    public CannotUpdateGroupWordBookNameException(String message) {
        super(message + " is Wrong Input. Name cannot consisting only numeric");
    }
}
