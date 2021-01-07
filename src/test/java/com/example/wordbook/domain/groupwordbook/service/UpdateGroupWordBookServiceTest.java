package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UpdateGroupWordBookServiceTest {

    @InjectMocks
    private UpdateGroupWordBookService updateGroupWordBookService;

    @Mock
    private GetGroupWordBookService getGroupWordBookService;

    @Mock
    private GroupWordBookRepository groupWordBookRepository;

    private final Long wordBookId = 0L;
    private GroupWordBook groupWordBook;

    @BeforeAll
    public void createGroupWordBook() {
        String nameConsistingWellFormed = "WordBook";
        Long groupIdThatIsValid = 0L;

        groupWordBook = GroupWordBook.builder()
                .isUsing(true)
                .name(nameConsistingWellFormed)
                .groupId(groupIdThatIsValid)
                .build();
        groupWordBook.setId(wordBookId);
    }

    @Test
    @DisplayName("정상적으로 단어장의 이름을 변경하는 테스트")
    public void updateWordBookName() {
        //given
        String nameConsistingWellFormedAfterUpdate = "UpdateWordBook";

        GroupWordBookRequestDTO.Update updateGroupWordBookDTO = GroupWordBookRequestDTO.Update.builder()
                .id(wordBookId)
                .name(nameConsistingWellFormedAfterUpdate)
                .build();

        given(getGroupWordBookService.getEntityById(wordBookId)).willReturn(groupWordBook);
        given(groupWordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        GroupWordBookResponseDTO.Detail detailGroupWordBookDTO = updateGroupWordBookService.update_name(wordBookId, updateGroupWordBookDTO);

        //then
        assertThat(detailGroupWordBookDTO.getId()).isEqualTo(wordBookId);
        assertThat(detailGroupWordBookDTO.getName()).isEqualTo(nameConsistingWellFormedAfterUpdate);
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void updateWordBookName_ErrorTest_NameThatIsOnlyNumeric() {
        //given
        String nameConsistingWellFormedAfterUpdate = "123123";

        GroupWordBookRequestDTO.Update updateGroupWordBookDTO = GroupWordBookRequestDTO.Update.builder()
                .id(wordBookId)
                .name(nameConsistingWellFormedAfterUpdate)
                .build();

        given(getGroupWordBookService.getEntityById(wordBookId)).willReturn(groupWordBook);
        given(groupWordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Throwable throwable = catchThrowable(()->updateGroupWordBookService.update_name(wordBookId, updateGroupWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}