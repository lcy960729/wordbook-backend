package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.exception.NotFoundStudyGroupException;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.global.mapper.StudyGroupMapper;
import org.springframework.stereotype.Service;

@Service
public class GetStudyGroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    private final GetStudyService getStudyService;

    public GetStudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper studyGroupMapper, GetStudyService getStudyService) {
        this.studyGroupRepository = studyGroupRepository;
        this.studyGroupMapper = studyGroupMapper;
        this.getStudyService = getStudyService;
    }

    public StudyGroupDetailResponseDTO getDetailDTOById(Long userId, Long studyGroupId) throws Exception {
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        StudyGroup studyGroup = study.getStudyGroup();

        return studyGroupMapper.entityToResponseDTO(study, studyGroup);
    }

    public StudyGroup getEntityById(Long id) {
        return studyGroupRepository.findById(id).orElseThrow(NotFoundStudyGroupException::new);
    }
}
