package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.dto.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
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

    public StudyGroupDetailDTO getDetailDTOById(Long id) throws Exception {
        return studyGroupMapper.entityToDetailDTO(getEntityById(id));
    }

    public StudyGroup getEntityById(Long id) throws Exception {
        return studyGroupRepository.findById(id).orElseThrow(Exception::new);
    }

}
