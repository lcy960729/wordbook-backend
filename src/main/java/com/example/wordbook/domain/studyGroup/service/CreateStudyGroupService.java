package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.study.service.CreateStudyService;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.mapper.CreateDtoToStudyGroupMapper;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateStudyGroupService {

    private final StudyGroupRepository studyGroupRepository;

    private final CreateDtoToStudyGroupMapper createDtoToStudyGroupMapper;
    private final StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper;

    private final GetUserService getUserService;
    private final CreateStudyService createStudyService;

    public CreateStudyGroupService(StudyGroupRepository studyGroupRepository, CreateDtoToStudyGroupMapper createDtoToStudyGroupMapper, StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper, GetUserService getUserService, CreateStudyService createStudyService) {
        this.studyGroupRepository = studyGroupRepository;
        this.createDtoToStudyGroupMapper = createDtoToStudyGroupMapper;
        this.studyToStudyGroupDetailDtoMapper = studyToStudyGroupDetailDtoMapper;
        this.getUserService = getUserService;
        this.createStudyService = createStudyService;
    }

    @Transactional
    public StudyGroupDetailDTO create(Long userId, CreateStudyGroupDTO createStudyGroupDTO) {
        StudyGroup studyGroup = createDtoToStudyGroupMapper.createDTOToEntity(createStudyGroupDTO);
        studyGroup.use();
        studyGroup = studyGroupRepository.save(studyGroup);

        User user = getUserService.getEntityById(userId);

        Study study = createStudyService.create(user, studyGroup);

        user.joinToStudy(study);
        studyGroup.addStudy(study);

        return studyToStudyGroupDetailDtoMapper.entityToStudGroupResponseDTO(study);
    }
}
