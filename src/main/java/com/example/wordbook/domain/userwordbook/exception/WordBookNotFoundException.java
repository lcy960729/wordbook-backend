package com.example.wordbook.domain.userwordbook.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class WordBookNotFoundException extends EntityNotFoundException {
    public WordBookNotFoundException(String message) {
        super(message + " is not found");
    }
}
