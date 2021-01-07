package com.example.wordbook.domain.groupwordbook.dto;

import com.example.wordbook.global.validators.annotation.NotOnlyNumeric;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GroupWordBookRequestDTO {
    @NoArgsConstructor
    @Data
    public static class Create {
        @NotBlank
        @NotOnlyNumeric
        private String name;

        @NotNull
        private Long groupId;

        @Builder
        public Create(String name, Long groupId) {
            this.name = name;
            this.groupId = groupId;
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
