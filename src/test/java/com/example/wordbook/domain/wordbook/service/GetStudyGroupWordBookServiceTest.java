package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.exception.WordBookNotFoundException;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.GetStudyGroupWordBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class GetStudyGroupWordBookServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GetStudyGroupWordBookServiceTest.class);

    @InjectMocks
    private GetStudyGroupWordBookService getStudyGroupWordBookService;

    @Mock
    private WordBookRepository wordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long groupWordBookId = 0L;
    private StudyGroupWordBook studyGroupWordBook;

    @BeforeAll
    public void createGroupWordBook() {
        String nameConsistingWellFormed = "testWordBook";
        StudyGroup studyGroup = StudyGroup.builder().build();

        studyGroupWordBook = StudyGroupWordBook.builder()
                .isUsing(true)
                .name(nameConsistingWellFormed)
                .studyGroup(studyGroup)
                .build();
        studyGroupWordBook.setId(groupWordBookId);
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 불러오는 테스트")
    public void getById() throws JsonProcessingException {
        //given
        Optional<WordBook> optionalGroupWordBook = Optional.of(this.studyGroupWordBook);

        Long idThatIsCorrect = groupWordBookId;
        given(wordBookRepository.findById(idThatIsCorrect)).willReturn(optionalGroupWordBook);

        //when
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityById(idThatIsCorrect);

        //then
        logger.info(objectMapper.writeValueAsString(studyGroupWordBook));
        assertThat(studyGroupWordBook)
                .usingRecursiveComparison()
                .isEqualTo(this.studyGroupWordBook);
    }

    @Test
    @DisplayName("아이디가 null 값인 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NullInput() {
        //given
        Optional<WordBook> optionalGroupWordBook = Optional.of(studyGroupWordBook);

        Long idThatIsNull = null;
        given(wordBookRepository.findById(idThatIsNull)).willReturn(optionalGroupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getStudyGroupWordBookService.getEntityById(groupWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 아이디의 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NotFoundById_Input() {
        //given
        //저장된 유저 워드북의 아이디는 0L
        Optional<WordBook> optionalGroupWordBook = Optional.of(studyGroupWordBook);

        Long idThatDoesNotExist = 1L;
        given(wordBookRepository.findById(idThatDoesNotExist)).willReturn(optionalGroupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getStudyGroupWordBookService.getEntityById(groupWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }
}