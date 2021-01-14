package com.example.wordbook.domain.wordbook.dto.request;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CreateWordBookDTO {
    @NotBlank
    @NotOnlyNumeric
    private String name;

    @Builder
    public CreateWordBookDTO(@NotBlank String name) {
        this.name = name;
    }
}
