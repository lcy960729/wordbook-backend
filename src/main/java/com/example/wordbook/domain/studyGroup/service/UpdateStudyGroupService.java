package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.dto.request.UpdateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateStudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper;
    private final GetStudyService getStudyService;

    public UpdateStudyGroupService(StudyGroupRepository studyGroupRepository, GetStudyService getStudyService, StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper) {
        this.studyGroupRepository = studyGroupRepository;
        this.getStudyService = getStudyService;
        this.studyToStudyGroupDetailDtoMapper = studyToStudyGroupDetailDtoMapper;
    }

    public StudyGroupDetailDTO update(@NotNull Long adminId, @NotNull Long studyGroupId, @Valid UpdateStudyGroupDTO updateStudyGroupDTO) {
        Study study = getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(adminId, studyGroupId);

        StudyGroup studyGroup = study.getStudyGroup();
        studyGroup.setName(updateStudyGroupDTO.getName());

        studyGroup = studyGroupRepository.save(studyGroup);

        return studyToStudyGroupDetailDtoMapper.entityToStudGroupResponseDTO(study);
    }
}
