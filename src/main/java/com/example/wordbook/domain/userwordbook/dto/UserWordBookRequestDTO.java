package com.example.wordbook.domain.userwordbook.dto;

import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserWordBookRequestDTO {
    @NoArgsConstructor
    @Data
    public static class Create {
        @NotBlank
        @NotOnlyNumeric
        private String name;

        @NotNull
        private Long userId;

        @Builder
        public Create(String name, Long userId) {
            this.name = name;
            this.userId = userId;
        }
    }

    @NoArgsConstructor
    @Data
    public static class Update {
        @NotNull
        private Long id;

        @NotBlank
        @NotOnlyNumeric
        private String name;

        @Builder
        public Update(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
