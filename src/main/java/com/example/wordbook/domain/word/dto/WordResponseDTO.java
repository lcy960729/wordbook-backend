package com.example.wordbook.domain.word.dto;

import com.example.wordbook.domain.word.entity.Word;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class WordResponseDTO {
    @NoArgsConstructor
    @Data
    public static class Detail {
        private Long id;

        private Boolean isUsing;

        private String voca;

        private String meaning;

        @Builder
        public Detail(Long id, Boolean isUsing, String voca, String meaning) {
            this.id = id;
            this.isUsing = isUsing;
            this.voca = voca;
            this.meaning = meaning;
        }
    }
}
