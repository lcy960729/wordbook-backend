package com.example.wordbook.domain.user.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

import java.util.function.Supplier;

public class UserNotFoundException extends EntityNotFoundException  {
    public UserNotFoundException() {
        super("");
    }
}
