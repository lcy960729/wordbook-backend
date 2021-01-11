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
public class CreateWordBookDTO {
    @NotBlank
    @NotOnlyNumeric
    private String name;

    @NotNull
    private WordBookType wordBookType;

    @NotNull
    private Long ownerId;

    @Builder
    public CreateWordBookDTO(@NotBlank String name, @NotNull WordBookType wordBookType, @NotNull Long ownerId) {
        this.name = name;
        this.wordBookType = wordBookType;
        this.ownerId = ownerId;
    }
}
