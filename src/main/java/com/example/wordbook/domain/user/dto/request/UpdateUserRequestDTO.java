package com.example.wordbook.domain.user.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateUserRequestDTO {
    @NotBlank
    private String name;

    @Builder
    public UpdateUserRequestDTO(@NotBlank String name) {
        this.name = name;
    }
}
