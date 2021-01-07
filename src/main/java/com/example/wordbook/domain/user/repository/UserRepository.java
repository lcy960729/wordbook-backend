package com.example.wordbook.domain.user.repository;

import com.example.wordbook.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
