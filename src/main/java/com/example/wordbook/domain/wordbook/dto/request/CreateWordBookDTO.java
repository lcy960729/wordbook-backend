package com.example.wordbook.domain.wordbook.dto.request;

import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class CreateWordBookDTO {
    @NotBlank
    private String name;

    @Builder
    public CreateWordBookDTO(String name) {
        this.name = name;
    }
}
