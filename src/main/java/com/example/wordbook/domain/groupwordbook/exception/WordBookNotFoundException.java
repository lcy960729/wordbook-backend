package com.example.wordbook.domain.groupwordbook.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class WordBookNotFoundException extends EntityNotFoundException {
    public WordBookNotFoundException(String message) {
        super(message + " is not found");
    }
}
