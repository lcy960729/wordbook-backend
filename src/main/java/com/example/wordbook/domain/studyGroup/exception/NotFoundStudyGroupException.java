package com.example.wordbook.domain.studyGroup.exception;

import com.example.wordbook.global.exception.EntityNotFoundException;

public class NotFoundStudyGroupException extends EntityNotFoundException {

    public NotFoundStudyGroupException() {
        super("StudyGroup");
    }
}
