package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class GetUserWordBookServiceTest extends UserWordBookServiceTest {
    @Mock
    private UserWordBookRepository userWordBookRepository;

    @Test
    @DisplayName("정상적으로 개인 단어장의 Entity를 불러오는 테스트")
    public void getById() {
        //given
        GetUserWordBookService getUserWordBookService = new GetUserWordBookService(userWordBookRepository, userWordBookToWordBookDetailDtoMapper);

        UserWordBook _userWordBook = domainFactory.getUserWordBook();

        Long userId = _userWordBook.getUser().getId();
        Long userWordBookId = _userWordBook.getId();

        given(userWordBookRepository.findByIdAndUserId(anyLong(), anyLong())).willReturn(Optional.of(_userWordBook));

        //when
        UserWordBook userWordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, userWordBookId);

        //then
        assertThat(userWordBook).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 아이디의 개인 단어장을 불러올시 에러 발생 테스트")
    public void getById_ErrorTest_NullInput() {
        //given
        GetUserWordBookService getUserWordBookService = new GetUserWordBookService(userWordBookRepository, userWordBookToWordBookDetailDtoMapper);

        long userId = 0L;
        long notInvalidUserWordBookId = 0L;

        given(userWordBookRepository.findByIdAndUserId(anyLong(), anyLong())).willThrow(NotFoundWordBookException.class);

        //when
        Throwable throwable = catchThrowable(() -> getUserWordBookService.getEntityByUserIdAndWordBookId(userId, notInvalidUserWordBookId));

        //then
        assertThat(throwable).isInstanceOf(NotFoundWordBookException.class);
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 WordDetailDTO로 불러오는 테스트")
    void getDetailDTOByUserIdAndWordBookId() {
        //given
        GetUserWordBookService getUserWordBookService = new GetUserWordBookService(userWordBookRepository, userWordBookToWordBookDetailDtoMapper);

        UserWordBook userWordBook = domainFactory.getUserWordBook();

        Long userId = userWordBook.getUser().getId();
        Long userWordBookId = userWordBook.getId();

        given(userWordBookRepository.findByIdAndUserId(anyLong(), anyLong())).willReturn(Optional.of(userWordBook));

        //when
        WordBookDetailDTO wordBookDetailDTO = getUserWordBookService.getDetailDTOByUserIdAndWordBookId(userId, userWordBookId);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getId()).isEqualTo(userWordBookId);
    }
}