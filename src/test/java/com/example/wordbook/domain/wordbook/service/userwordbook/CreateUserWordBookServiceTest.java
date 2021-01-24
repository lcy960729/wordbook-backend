package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToUserWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.userwordbook.CreateUserWordBookService;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class CreateUserWordBookServiceTest extends UserWordBookServiceTest {
    @Mock
    private WordBookRepository wordBookRepository;

    @Mock
    private GetUserService getUserService;

    @Test
    @DisplayName("정상적으로 개인 단어장을 생성하는 테스트")
    public void createUserWordBook() {
        //given
        CreateUserWordBookService createUserWordBookService = new CreateUserWordBookService(
                wordBookRepository,
                userWordBookToWordBookDetailDtoMapper,
                getUserService,
                createDtoToUserWordBookMapper);

        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO.builder()
                .name("testWordBook")
                .build();

        UserWordBook userWordBook = domainFactory.getUserWordBook();
        userWordBook.setName(createWordBookDTO.getName());

        User user = userWordBook.getUser();

        given(getUserService.getEntityById(anyLong())).willReturn(user);
        given(wordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        WordBookDetailDTO wordBookDetailDTO = createUserWordBookService.create(user.getId(), createWordBookDTO);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getName()).isEqualTo(createWordBookDTO.getName());
    }
}