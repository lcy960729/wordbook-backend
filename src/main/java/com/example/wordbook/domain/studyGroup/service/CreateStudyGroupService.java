package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.dto.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.study.CreateStudyService;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.global.mapper.StudyGroupMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateStudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    private final GetUserService getUserService;
    private final CreateStudyService createStudyService;

    public CreateStudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper studyGroupMapper, GetUserService getUserService, CreateStudyService createStudyService) {
        this.studyGroupRepository = studyGroupRepository;
        this.studyGroupMapper = studyGroupMapper;
        this.createStudyService = createStudyService;
        this.getUserService = getUserService;
    }

    @Transactional
    public Long create(CreateStudyGroupDTO createStudyGroupDTO) throws Exception {
        StudyGroup studyGroup = studyGroupMapper.createDTOToEntity(createStudyGroupDTO);
        studyGroup.use();
        studyGroup = studyGroupRepository.save(studyGroup);

        User user = getUserService.getEntityById(createStudyGroupDTO.getGroupOwnerId());

        Study study = createStudyService.create(user, studyGroup);

        user.participateInStudyGroup(study);
        studyGroup.addStudy(study);

        return studyGroup.getId();
    }
}
