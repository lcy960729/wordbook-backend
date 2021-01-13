package com.example.wordbook.domain.studyGroup.service;

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

    public GetStudyGroupService(StudyGroupRepository studyGroupRepository, StudyGroupMapper studyGroupMapper) {
        this.studyGroupRepository = studyGroupRepository;
        this.studyGroupMapper = studyGroupMapper;
    }

    public StudyGroupDetailResponseDTO getDetailDTOById(Long userId, Long studyGroupId) throws Exception {
        return studyGroupMapper.entityToDetailDTO(userId, getEntityById(studyGroupId));
    }

    public StudyGroup getEntityById(Long id) {
        return studyGroupRepository.findById(id).orElseThrow(NotFoundStudyGroupException::new);
    }
}
