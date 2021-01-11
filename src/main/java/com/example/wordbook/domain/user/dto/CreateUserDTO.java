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
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;

    @Builder
    public CreateUserDTO(String email, String pw, String name) {
        this.email = email;
        this.pw = pw;
        this.name = name;
    }
}
