package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.DeleteStudyService;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudyGroupService {

    private final GetStudyService getStudyService;

    private final DeleteStudyService deleteStudyService;

    private final StudyGroupRepository studyGroupRepository;

    public DeleteStudyGroupService(GetStudyService getStudyService, DeleteStudyService deleteStudyService, StudyGroupRepository studyGroupRepository) {
        this.getStudyService = getStudyService;
        this.deleteStudyService = deleteStudyService;
        this.studyGroupRepository = studyGroupRepository;
    }

    public void delete(Long userId, Long studyGroupId) throws Exception {
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        if (study.getStudyGroupRole() != StudyGroupRole.ADMIN)
            throw new Exception();

        StudyGroup studyGroup = study.getStudyGroup();

        //TODO 단어장 삭제도 해야함.

        studyGroup.getStudyList().forEach((s) -> {
            User user = s.getUser();
            user.signOutToStudy(s);

            deleteStudyService.deleteByEntity(s.getId());
        });

        deleteStudyGroupEntity(studyGroupId);
    }

    private void deleteStudyGroupEntity(Long studyGroupId) {
        studyGroupRepository.deleteById(studyGroupId);
    }

}
