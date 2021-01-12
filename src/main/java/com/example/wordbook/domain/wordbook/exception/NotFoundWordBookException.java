package com.example.wordbook.domain.wordbook.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class NotFoundWordBookException extends EntityNotFoundException {
    public NotFoundWordBookException() {
        super("WordBook is not found");
    }
}
