package com.example.wordbook.domain.word.service.word_userwordbook;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.exception.NotFoundWordException;
import com.example.wordbook.domain.word.service.userwordbook.GetWordOfUserWordBookService;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class GetWordOfUserWordBookServiceTest extends WordOfUserWordBookServiceTest {

    @Mock
    private GetUserWordBookService getUserWordBookService;

    @Test
    @DisplayName("정상적으로 개인 단어장에 단어를 반환하는 테스트")
    public void getWordAtUserWordBook() {
        //given
        GetWordOfUserWordBookService getWordOfUserWordBookService = new GetWordOfUserWordBookService(getUserWordBookService);

        UserWordBook userWordBook = domainFactory.getUserWordBook();
        Word _word = userWordBook.getWords().get(0);

        given(getUserWordBookService.getEntityByUserIdAndWordBookId(anyLong(), anyLong()))
                .willReturn(userWordBook);

        //when
        long userId = userWordBook.getUser().getId();
        long wordBookId = userWordBook.getId();
        long wordId = _word.getId();
        Word word = getWordOfUserWordBookService.getEntityByUserIdAndWordBookIdAndWordId(userId, wordBookId, wordId);

        //then
        assertThat(word).isNotNull();
        assertThat(word.getId()).isEqualTo(_word.getId());
        assertThat(word.getVoca()).isEqualTo(_word.getVoca());
        assertThat(word.getMeaning()).isEqualTo(_word.getMeaning());
    }

    @Test
    @DisplayName("유효하지 않은 단어를 개인단어장에서 조회시 에러 반환 테스트")
    public void getWordAtUserWordBook_NotInvalidWord_ErrorTest() {
        //given
        GetWordOfUserWordBookService getWordOfUserWordBookService = new GetWordOfUserWordBookService(getUserWordBookService);

        UserWordBook userWordBook = domainFactory.getUserWordBook();
        userWordBook.getWords().clear();

        given(getUserWordBookService.getEntityByUserIdAndWordBookId(anyLong(), anyLong()))
                .willReturn(userWordBook);

        //when
        long userId = 0L;
        long wordId = 0L;
        long wordBookId = userWordBook.getId();

        Throwable throwable = catchThrowable(() ->
                getWordOfUserWordBookService.getEntityByUserIdAndWordBookIdAndWordId(userId, wordBookId, wordId)
        );

        //then
        assertThat(throwable).isInstanceOf(NotFoundWordException.class);
    }
}
