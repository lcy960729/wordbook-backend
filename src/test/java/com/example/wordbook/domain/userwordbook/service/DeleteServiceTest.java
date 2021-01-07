package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DeleteServiceTest {

    @InjectMocks
    private UpdateUserWordBookService updateUserWordBookService;

    @Mock
    private GetUserWordBookService getUserWordBookService;

    @Mock
    private UserWordBookRepository userWordBookRepository;

    private final Long wordBookId = 0L;
    private UserWordBook userWordBook;

    @BeforeEach
    public void createUserWordBook() {
        String nameConsistingWellFormed = "WordBook";

        userWordBook = UserWordBook.builder()
                .isUsing(true)
                .name(nameConsistingWellFormed)
                .userId(User.builder().build())
                .build();
        userWordBook.setId(wordBookId);
    }

    @Test
    @DisplayName("정상적으로 단어장의 이름을 변경하는 테스트")
    public void updateWordBookName() {
        //given
        String nameConsistingWellFormedAfterUpdate = "UpdateWordBook";

        UserWordBookRequestDTO.Update updateUserWordBookDTO = UserWordBookRequestDTO.Update.builder()
                .id(wordBookId)
                .name(nameConsistingWellFormedAfterUpdate)
                .build();

        given(getUserWordBookService.getEntityById(wordBookId)).willReturn(userWordBook);
        given(userWordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        UserWordBookResponseDTO.Detail detailUserWordBookDTO = updateUserWordBookService.update_name(wordBookId, updateUserWordBookDTO);

        //then
        assertThat(detailUserWordBookDTO.getId()).isEqualTo(wordBookId);
        assertThat(detailUserWordBookDTO.getName()).isEqualTo(nameConsistingWellFormedAfterUpdate);
    }
}