package com.example.wordbook.NotUseClass.wordbook;

import javax.validation.Valid;

public interface UpdateWordBookService <K, V>{
    public K update_name(Long id, @Valid V updateGroupWordBookDTO);
}