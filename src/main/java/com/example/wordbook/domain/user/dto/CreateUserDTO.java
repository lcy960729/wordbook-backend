package com.example.wordbook.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateUserDTO {
    @NotNull
    private String userId;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;

    @Builder
    public CreateUserDTO(String userId, String pw, String name) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
    }
}
