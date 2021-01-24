package com.example.wordbook.domain.word.service.word_userwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.service.userwordbook.GetWordOfUserWordBookService;
import com.example.wordbook.domain.word.service.userwordbook.UpdateWordOfUserWordBookService;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class UpdateWordOfUserWordBookServiceTest extends WordOfUserWordBookServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private GetWordOfUserWordBookService getWordOfUserWordBookService;

    @Test
    @DisplayName("정상적으로 개인 단어장에 단어를 수정하는 테스트")
    public void updateWordAtUserWordBook() {
        //given
        UpdateWordOfUserWordBookService updateWordOfUserWordBookService = new UpdateWordOfUserWordBookService(
                wordRepository,
                wordOfUserWordBookToWordDetailDtoMapper,
                getWordOfUserWordBookService
        );

        UserWordBook userWordBook = domainFactory.getUserWordBook();

        UpdateWordDTO updateWordDTO = UpdateWordDTO.builder()
                .voca("apple")
                .meaning("사과")
                .build();

        Word word = userWordBook.getWords().get(0);

        given(wordRepository.save(any(Word.class))).willReturn(word);
        given(getWordOfUserWordBookService.getEntityByUserIdAndWordBookIdAndWordId(anyLong(), anyLong(), anyLong()))
                .willReturn(word);

        //when
        Long user = userWordBook.getUser().getId();
        Long wordBookId = userWordBook.getId();
        Long wordId = word.getId();

        WordDetailDTO wordDetailDTO = updateWordOfUserWordBookService.update(user, wordBookId, wordId, updateWordDTO);

        //then
        assertThat(wordDetailDTO).isNotNull();
        assertThat(wordDetailDTO.getId()).isEqualTo(word.getId());
        assertThat(wordDetailDTO.getVoca()).isEqualTo(word.getVoca());
        assertThat(wordDetailDTO.getMeaning()).isEqualTo(word.getMeaning());
    }
}
