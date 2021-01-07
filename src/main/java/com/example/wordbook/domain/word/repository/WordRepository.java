package com.example.wordbook.domain.word.repository;

import com.example.wordbook.domain.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
