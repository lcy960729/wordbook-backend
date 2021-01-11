package com.example.wordbook.domain.wordbook.dto;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeleteWordBookDTO {
    private WordBookType wordBookType;

    @Builder
    public DeleteWordBookDTO(WordBookType wordBookType) {
        this.wordBookType = wordBookType;
    }
}
