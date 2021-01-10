package com.example.wordbook.domain.wordbook.service.wordbook;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;

import javax.validation.Valid;

public interface CreateWordBookService {
    public Long create(@Valid WordBookRequestDTO.Create createWordBookDTO);
}
