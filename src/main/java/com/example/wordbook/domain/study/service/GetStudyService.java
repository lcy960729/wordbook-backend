package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class GetStudyService {

    private final StudyRepository studyRepository;

    public GetStudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Study getEntityByFindByUserIdAndStudyGroupsId(Long userId, Long studyGroupId) {
        return studyRepository.findByUserIdAndStudyGroupId(userId, studyGroupId)
                .orElseThrow(NotFoundStudyException::new);
    }

    public Study getEntityByFindByAdminIdAndStudyGroupsId(Long adminId, Long studyGroupId) {
        Study study = getEntityByFindByUserIdAndStudyGroupsId(adminId, studyGroupId);

        if (!study.isAdmin()){
            throw new BusinessException(ErrorCode.HAS_NOT_ADMIN_PERMISSION_TO_ACCESS_STUDYGROUP_WORDBOOK);
        }

        return study;
    }
}
