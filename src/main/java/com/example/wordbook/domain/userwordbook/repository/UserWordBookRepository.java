package com.example.wordbook.domain.userwordbook.repository;

import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWordBookRepository extends JpaRepository<UserWordBook, Long> {
}
