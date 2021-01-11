package com.example.wordbook.domain.studyGroup.repository;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
}
