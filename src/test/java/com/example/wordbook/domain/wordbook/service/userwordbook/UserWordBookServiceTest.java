package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.wordbook.mapper.CreateDtoToStudyGroupWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToUserWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserWordBookServiceTest {
    protected final UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper = Mappers.getMapper(UserWordBookToWordBookDetailDtoMapper.class);
    protected final CreateDtoToUserWordBookMapper createDtoToUserWordBookMapper = Mappers.getMapper(CreateDtoToUserWordBookMapper.class);

    protected final DomainFactory domainFactory = new DomainFactory();
}
