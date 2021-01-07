package com.example.wordbook.domain.userwordbook.exception;

import com.example.wordbook.global.exception.ValidationException;

public class CannotUpdateUserWordBookNameException extends ValidationException {

    public CannotUpdateUserWordBookNameException(String message) {
        super(message + " is Wrong Input. Name cannot consisting only numeric");
    }
}
