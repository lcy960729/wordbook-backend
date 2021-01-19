package com.example.wordbook.domain.user.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

import java.util.function.Supplier;

public class NotFoundUserException extends EntityNotFoundException  {
    public NotFoundUserException() {
        super("User");
    }
}
