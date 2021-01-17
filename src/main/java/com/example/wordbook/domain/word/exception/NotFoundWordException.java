package com.example.wordbook.domain.word.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class NotFoundWordException extends EntityNotFoundException {
    public NotFoundWordException() {
        super("");
    }
}
