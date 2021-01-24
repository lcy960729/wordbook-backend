package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class UpdateUserWordBookServiceTest extends UserWordBookServiceTest {

    @Mock
    private UserWordBookRepository userWordBookRepository;

    @Mock
    private GetUserWordBookService getUserWordBookService;

    @Test
    @DisplayName("정상적으로 개인 단어장을 수정하는 테스트")
    public void updateUserWordBook() {
        //given
        UpdateUserWordBookService createUserWordBookService = new UpdateUserWordBookService(
                getUserWordBookService,
                userWordBookRepository,
                userWordBookToWordBookDetailDtoMapper);

        UpdateWordBookDTO updateWordBookDTO = UpdateWordBookDTO.builder()
                .name("updateTestWordBookName")
                .build();

        UserWordBook userWordBook = domainFactory.getUserWordBook();
        userWordBook.setName(updateWordBookDTO.getName());

        User user = userWordBook.getUser();

        given(getUserWordBookService.getEntityByUserIdAndWordBookId(anyLong(), anyLong())).willReturn(userWordBook);
        given(userWordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        WordBookDetailDTO wordBookDetailDTO = createUserWordBookService.update_name(user.getId(), userWordBook.getId(), updateWordBookDTO);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getName()).isEqualTo(updateWordBookDTO.getName());
    }

}