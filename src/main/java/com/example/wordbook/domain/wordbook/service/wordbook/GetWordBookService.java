package com.example.wordbook.domain.wordbook.service.wordbook;

public interface GetWordBookService<K, V> {
    public K getDetailDTOById(Long id);
    public V getEntityById(Long id);
}
