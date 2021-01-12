package com.example.wordbook.domain.wordbook.service.wordbook;

public interface GetWordBookService<K, V> {
     K getDetailDTOById(Long id);
     V getEntityById(Long id);
}
