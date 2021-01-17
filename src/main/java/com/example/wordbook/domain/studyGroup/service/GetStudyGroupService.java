package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class GetStudyGroupService {
    private final StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper;

    private final GetStudyService getStudyService;

    public GetStudyGroupService(GetStudyService getStudyService, StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper) {
        this.getStudyService = getStudyService;
        this.studyToStudyGroupDetailDtoMapper = studyToStudyGroupDetailDtoMapper;
    }

    public StudyGroupDetailDTO getDetailDTOByUserIdAndStudyGroupId(Long userId, Long studyGroupId) {
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        return studyToStudyGroupDetailDtoMapper.entityToStudGroupResponseDTO(study);
    }
}
