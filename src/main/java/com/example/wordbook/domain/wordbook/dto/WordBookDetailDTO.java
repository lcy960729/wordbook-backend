package com.example.wordbook.domain.wordbook.dto;

import com.example.wordbook.domain.word.entity.Word;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
public class WordBookDetailDTO {
    private Long id;

    private Long ownerId;

    private Boolean isUsing;

    private String name;

    private List<Word> words;

    @Builder
    public WordBookDetailDTO(Long id, @NotNull Long ownerId, Boolean isUsing, String name, List<Word> words) {
        this.id = id;
        this.ownerId = ownerId;
        this.isUsing = isUsing;
        this.name = name;
        this.words = words;
    }
}
