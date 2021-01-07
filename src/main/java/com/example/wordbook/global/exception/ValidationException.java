package com.example.wordbook.global.exception;

import com.example.wordbook.global.enums.ErrorCode;

public class ValidationException extends BusinessException {

    public ValidationException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
