package com.example.wordbook.domain.word.service.word_studygroupwordbook;


import com.example.wordbook.domain.word.controller.WordOfStudyGroupWordBookController;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.mapper.CreateDtoToWordMapper;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.mapper.WordOfUserWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.service.studygroupwordbook.AddWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.DeleteWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.UpdateWordOfStudyGroupWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class WordOfStudyGroupWordBookServiceTest {
    protected CreateDtoToWordMapper createDtoToWordMapper = Mappers.getMapper(CreateDtoToWordMapper.class);
    protected WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper = Mappers.getMapper(WordOfStudyGroupWordBookToWordDetailDtoMapper.class);

    protected DomainFactory domainFactory = new DomainFactory();
}
