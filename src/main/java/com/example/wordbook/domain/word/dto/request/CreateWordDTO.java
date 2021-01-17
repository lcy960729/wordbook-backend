package com.example.wordbook.domain.word.dto.request;

import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CreateWordDTO {
    @NotBlank
    private String voca;

    @NotBlank
    private String meaning;

    @Builder
    public CreateWordDTO(String voca, String meaning) {
        this.voca = voca;
        this.meaning = meaning;
    }
}
