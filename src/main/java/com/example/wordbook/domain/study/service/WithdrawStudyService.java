package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawStudyService {
    private final StudyRepository studyRepository;

    public WithdrawStudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public void withdraw(User user, StudyGroup studyGroup) {
        Optional<Study> optionalStudy = studyRepository.findByUserIdAndStudyGroupId(user.getId(), studyGroup.getId());

        Study study = optionalStudy.orElseThrow(NotFoundStudyException::new);
        studyRepository.delete(study);

        user.getStudyList().remove(study);
        studyGroup.getStudyList().remove(study);
    }
}
