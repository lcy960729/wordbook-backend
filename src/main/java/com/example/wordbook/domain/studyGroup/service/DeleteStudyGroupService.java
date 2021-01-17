package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.DeleteStudyService;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudyGroupService {

    private final GetStudyService getStudyService;
    private final StudyGroupRepository studyGroupRepository;

    public DeleteStudyGroupService(GetStudyService getStudyService, StudyGroupRepository studyGroupRepository) {
        this.getStudyService = getStudyService;
        this.studyGroupRepository = studyGroupRepository;
    }

    public void delete(Long adminId, Long studyGroupId) {
        Study study = getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(adminId, studyGroupId);

        studyGroupRepository.delete(study.getStudyGroup());
    }
}
