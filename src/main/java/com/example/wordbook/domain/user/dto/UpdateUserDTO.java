package com.example.wordbook.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateUserDTO {
    @NotBlank
    private String name;

    @Builder
    public UpdateUserDTO(@NotBlank String name) {
        this.name = name;
    }
}
