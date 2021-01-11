package com.example.wordbook.domain.study.repository;

import com.example.wordbook.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
