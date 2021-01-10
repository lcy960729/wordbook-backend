package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWordBookRepository extends JpaRepository<UserWordBook, Long> {
}
