package com.example.wordbook.NotUseClass.wordbook;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;

import javax.validation.Valid;

public interface CreateWordBookService {
    public Long create(@Valid CreateWordBookDTO createWordBookDTO);
}
