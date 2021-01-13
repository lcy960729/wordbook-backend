package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class JoinStudyService {
    private final StudyRepository studyRepository;

    public JoinStudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Study create(User user, StudyGroup studyGroup) {
        Study study = Study.builder()
                .studyGroup(studyGroup)
                .user(user)
                .build();
        return studyRepository.save(study);
    }
}
