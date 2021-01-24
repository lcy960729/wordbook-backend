package com.example.wordbook.domain.word.service.word_userwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.service.userwordbook.AddWordOfUserWordBookService;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class AddWordOfUserWordBookServiceTest extends WordOfUserWordBookServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private GetUserWordBookService getUserWordBookService;

    @Test
    @DisplayName("정상적으로 개인 단어장에 단어를 추가하는 테스트")
    public void addWordAtUserWordBook() {
        //given
        AddWordOfUserWordBookService addWordOfUserWordBookService = new AddWordOfUserWordBookService(
                wordRepository,
                wordOfUserWordBookToWordDetailDtoMapper,
                getUserWordBookService,
                createDtoToWordMapper);

        CreateWordDTO createWordDTO = CreateWordDTO.builder()
                .voca("test")
                .meaning("테스트")
                .build();

        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);
        word.setId(0L);

        UserWordBook userWordBook = domainFactory.getUserWordBook();

        given(wordRepository.save(any(Word.class))).willReturn(word);
        given(getUserWordBookService.getEntityByUserIdAndWordBookId(anyLong(), anyLong())).willReturn(userWordBook);

        //when
        Long user = userWordBook.getUser().getId();
        Long wordBookId = userWordBook.getId();

        WordDetailDTO wordDetailDTO = addWordOfUserWordBookService.add(user, wordBookId, createWordDTO);

        //then
        assertThat(wordDetailDTO).isNotNull();
        assertThat(wordDetailDTO.getId()).isEqualTo(word.getId());
    }
}
