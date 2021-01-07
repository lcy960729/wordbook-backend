package com.example.wordbook.domain.word.dto;

import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WordRequestDTO {
    @NoArgsConstructor
    @Data
    public static class Create {
        @NotBlank
        @NotOnlyNumeric
        private String voca;

        @NotBlank
        private String meaning;

        @NotNull
        private Long wordBookId;

        @Builder
        public Create(@NotBlank String voca, @NotBlank String meaning, @NotNull Long wordBookId) {
            this.voca = voca;
            this.meaning = meaning;
            this.wordBookId = wordBookId;
        }
    }

    @NoArgsConstructor
    @Data
    public static class Update {
        @NotBlank
        @NotOnlyNumeric
        private String voca;

        @NotBlank
        private String meaning;

        @NotNull
        private Long wordBookId;

        @Builder
        public Update(@NotBlank String voca, @NotBlank String meaning, @NotNull Long wordBookId) {
            this.voca = voca;
            this.meaning = meaning;
            this.wordBookId = wordBookId;
        }
    }
}
