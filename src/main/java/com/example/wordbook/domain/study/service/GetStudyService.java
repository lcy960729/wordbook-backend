package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetStudyService {

    private final StudyRepository studyRepository;

    public GetStudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Study getEntityByFindByUserIdAndStudyGroupsId(Long userId, Long studyGroupId){
        return studyRepository.findByUserIdAndStudyGroupId(userId, studyGroupId)
                .orElseThrow(NotFoundStudyException::new);
    }
}
