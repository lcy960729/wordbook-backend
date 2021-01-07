package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.exception.WordBookNotFoundException;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(MockitoExtension.class)
public class GetServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GetServiceTest.class);

    @InjectMocks
    private GetUserWordBookService getUserWordBookService;

    @Mock
    private UserWordBookRepository userWordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long userWordBookId = 0L;
    private UserWordBook userWordBook;

    @BeforeEach
    public void createUserWordBook() {
        String nameConsistingWellFormed = "testWordBook";

        userWordBook = UserWordBook.builder()
                .isUsing(true)
                .name(nameConsistingWellFormed)
                .userId(User.builder().build())
                .build();
        userWordBook.setId(userWordBookId);
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 불러오는 테스트")
    public void getById() throws JsonProcessingException {
        //given
        Optional<UserWordBook> optionalUserWordBook = Optional.of(userWordBook);

        Long idThatIsCorrect = userWordBookId;
        given(userWordBookRepository.findById(idThatIsCorrect)).willReturn(optionalUserWordBook);

        //when
        UserWordBook userWordBook = getUserWordBookService.getEntityById(idThatIsCorrect);

        //then
        logger.info(objectMapper.writeValueAsString(userWordBook));
        assertThat(userWordBook)
                .usingRecursiveComparison()
                .isEqualTo(this.userWordBook);
    }

    @Test
    @DisplayName("아이디가 null 값인 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NullInput() {
        //given
        Optional<UserWordBook> optionalUserWordBook = Optional.of(userWordBook);

        Long idThatIsNull = null;
        given(userWordBookRepository.findById(idThatIsNull)).willReturn(optionalUserWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getUserWordBookService.getEntityById(userWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 아이디의 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NotFoundById_Input() {
        //given
        //저장된 유저 워드북의 아이디는 0L
        Optional<UserWordBook> optionalUserWordBook = Optional.of(userWordBook);

        Long idThatDoesNotExist = 1L;
        given(userWordBookRepository.findById(idThatDoesNotExist)).willReturn(optionalUserWordBook);

        //when
        Throwable throwable = catchThrowable(() -> getUserWordBookService.getEntityById(userWordBookId));

        //then
        assertThat(throwable).isInstanceOf(WordBookNotFoundException.class);
    }
}