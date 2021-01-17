package com.example.wordbook.NotUseClass.wordbook;

public interface GetWordBookService<K, V> {
     K getDetailDTOById(Long id);
     V getEntityById(Long id);
}
