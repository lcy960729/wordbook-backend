package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWordBookRepository extends JpaRepository<UserWordBook, Long> {
    Optional<UserWordBook> findByIdAndUserId(Long wordBookId, Long userId);
}
