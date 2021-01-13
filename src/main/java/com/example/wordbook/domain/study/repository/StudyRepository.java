package com.example.wordbook.domain.study.repository;

import com.example.wordbook.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Optional<Study> findByUserIdAndStudyGroupId(Long userId, Long studyGroupId);
}
