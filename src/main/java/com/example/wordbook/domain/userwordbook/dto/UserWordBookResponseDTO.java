package com.example.wordbook.domain.userwordbook.dto;

import com.example.wordbook.domain.word.entity.Word;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserWordBookResponseDTO {
    @NoArgsConstructor
    @Data
    public static class Detail {
        private Long id;

        private Long userId;

        private Boolean isUsing;

        private String name;

        private List<Word> words;

        @Builder
        public Detail(Long id, @NotNull Long userId, Boolean isUsing, String name, List<Word> words) {
            this.id = id;
            this.userId = userId;
            this.isUsing = isUsing;
            this.name = name;
            this.words = words;
        }
    }
}
