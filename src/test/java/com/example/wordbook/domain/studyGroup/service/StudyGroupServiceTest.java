package com.example.wordbook.domain.studyGroup.service;


import com.example.wordbook.domain.studyGroup.mapper.CreateDtoToStudyGroupMapper;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class StudyGroupServiceTest {
    protected final StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper = Mappers.getMapper(StudyToStudyGroupDetailDtoMapper.class);
    protected final CreateDtoToStudyGroupMapper createDtoToStudyGroupMapper = Mappers.getMapper(CreateDtoToStudyGroupMapper.class);
}
