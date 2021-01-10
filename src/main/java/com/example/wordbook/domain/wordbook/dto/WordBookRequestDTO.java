package com.example.wordbook.domain.wordbook.dto;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WordBookRequestDTO {
    @NoArgsConstructor
    @Data
    public static class Create {
        @NotBlank
        @NotOnlyNumeric
        private String name;

        private WordBookType wordBookType;

        @NotNull
        private Long ownerId;

        @Builder
        public Create(@NotBlank String name, WordBookType wordBookType, @NotNull Long ownerId) {
            this.name = name;
            this.wordBookType = wordBookType;
            this.ownerId = ownerId;
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
