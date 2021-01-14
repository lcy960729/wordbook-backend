package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.study.service.JoinStudyService;
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
    private final JoinStudyService joinStudyService;

    public CreateStudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper studyGroupMapper, GetUserService getUserService, JoinStudyService joinStudyService) {
        this.studyGroupRepository = studyGroupRepository;
        this.studyGroupMapper = studyGroupMapper;
        this.joinStudyService = joinStudyService;
        this.getUserService = getUserService;
    }

    @Transactional
    public StudyGroupDetailResponseDTO create(Long userId, CreateStudyGroupRequestDTO createStudyGroupRequestDTO) throws Exception {
        StudyGroup studyGroup = studyGroupMapper.createDTOToEntity(createStudyGroupRequestDTO);
        studyGroup.use();
        studyGroup = studyGroupRepository.save(studyGroup);

        User user = getUserService.getEntityById(userId);

        Study study = joinStudyService.create(user, studyGroup);


        user.joinToStudy(study);
        studyGroup.addStudy(study);

        return studyGroupMapper.entityToResponseDTO(study, studyGroup);
    }
}
