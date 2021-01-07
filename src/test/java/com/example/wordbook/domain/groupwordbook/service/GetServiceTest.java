package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.exception.WordBookNotFoundException;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class GetServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GetServiceTest.class);

    @InjectMocks
    private GetGroupWordBookService getGroupWordBookService;

    @Mock
    private GroupWordBookRepository groupWordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long groupWordBookId = 0L;
    private GroupWordBook groupWordBook;

    @BeforeAll
    public void createGroupWordBook() {
        String nameConsistingWellFormed = "testWordBook";
        Long groupIdThatIsValid = 0L;

        groupWordBook = GroupWordBook.builder()
                .isUsing(true)
                .name(nameConsistingWellFormed)
                .groupId(groupIdThatIsValid)
                .build();
        groupWordBook.setId(groupWordBookId);
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 불러오는 테스트")
    public void getById() throws JsonProcessingException {
        //given
        Optional<GroupWordBook> optionalGroupWordBook = Optional.of(groupWordBook);

        Long idThatIsCorrect = groupWordBookId;
        given(groupWordBookRepository.findById(idThatIsCorrect)).willReturn(optionalGroupWordBook);

        //when
        GroupWordBook groupWordBook = getGroupWordBookService.getEntityById(idThatIsCorrect);

        //then
        logger.info(objectMapper.writeValueAsString(groupWordBook));
        assertThat(groupWordBook)
                .usingRecursiveComparison()
                .isEqualTo(this.groupWordBook);
    }

    @Test
    @DisplayName("아이디가 null 값인 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NullInput() {
        //given
        Optional<GroupWordBook> optionalGroupWordBook = Optional.of(groupWordBook);

        Long idThatIsNull = null;
        given(groupWordBookRepository.findById(idThatIsNull)).willReturn(optionalGroupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getGroupWordBookService.getEntityById(groupWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 아이디의 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NotFoundById_Input() {
        //given
        //저장된 유저 워드북의 아이디는 0L
        Optional<GroupWordBook> optionalGroupWordBook = Optional.of(groupWordBook);

        Long idThatDoesNotExist = 1L;
        given(groupWordBookRepository.findById(idThatDoesNotExist)).willReturn(optionalGroupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getGroupWordBookService.getEntityById(groupWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }
}