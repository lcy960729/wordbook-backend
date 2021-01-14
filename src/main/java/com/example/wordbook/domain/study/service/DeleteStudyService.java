package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudyService {
    private final StudyRepository studyRepository;

    public DeleteStudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public void deleteByEntity(Long studyId){
        studyRepository.deleteById(studyId);
    }

}
