package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudyGroupWordBookRepository extends JpaRepository<StudyGroupWordBook, Long> {
}
