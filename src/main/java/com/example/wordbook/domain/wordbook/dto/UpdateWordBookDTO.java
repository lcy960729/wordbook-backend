package com.example.wordbook.domain.wordbook.dto;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class UpdateWordBookDTO {
    @NotNull
    private Long id;

    @NotNull
    private WordBookType wordBookType;

    @NotBlank
    @NotOnlyNumeric
    private String name;

    @Builder
    public UpdateWordBookDTO(@NotNull Long id, @NotNull WordBookType wordBookType, @NotBlank String name) {
        this.id = id;
        this.wordBookType = wordBookType;
        this.name = name;
    }
}
