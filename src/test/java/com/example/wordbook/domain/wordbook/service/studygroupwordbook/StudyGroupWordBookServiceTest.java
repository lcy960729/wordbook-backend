package com.example.wordbook.domain.wordbook.service.studygroupwordbook;

import com.example.wordbook.domain.studyGroup.mapper.CreateDtoToStudyGroupMapper;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToStudyGroupWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class StudyGroupWordBookServiceTest {
    protected final StudyGroupWordBookToWordBookDetailDtoMapper studyGroupWordBookToWordBookDetailDtoMapper = Mappers.getMapper(StudyGroupWordBookToWordBookDetailDtoMapper.class);
    protected final CreateDtoToStudyGroupWordBookMapper createDtoToStudyGroupWordBookMapper = Mappers.getMapper(CreateDtoToStudyGroupWordBookMapper.class);

    protected final DomainFactory domainFactory = new DomainFactory();
}
