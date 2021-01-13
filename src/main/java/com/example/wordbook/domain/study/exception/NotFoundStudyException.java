package com.example.wordbook.domain.study.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class NotFoundStudyException extends EntityNotFoundException {
    public NotFoundStudyException() {
        super("Study is not found");
    }
}
