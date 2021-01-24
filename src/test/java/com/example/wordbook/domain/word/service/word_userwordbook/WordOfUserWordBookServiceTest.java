package com.example.wordbook.domain.word.service.word_userwordbook;


import com.example.wordbook.domain.word.mapper.CreateDtoToWordMapper;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.mapper.WordOfUserWordBookToWordDetailDtoMapper;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class WordOfUserWordBookServiceTest {
    protected CreateDtoToWordMapper createDtoToWordMapper = Mappers.getMapper(CreateDtoToWordMapper.class);
    protected WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper = Mappers.getMapper(WordOfUserWordBookToWordDetailDtoMapper.class);

    protected DomainFactory domainFactory = new DomainFactory();
}
