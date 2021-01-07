package com.example.wordbook.NotUseClass;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CreateUserWordBookValidator {
    public void validate(UserWordBookRequestDTO.Create createUserWordBookDTO, Errors errors) {
        if (createUserWordBookDTO.getName().matches("^[0-9]+$")) {
            errors.rejectValue("name", "wrongValue", "name is not numeric");
            errors.reject( "wrongValue", "name is not numeric");
        }
    }
}