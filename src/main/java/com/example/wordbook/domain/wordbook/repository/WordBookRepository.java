package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {

}
