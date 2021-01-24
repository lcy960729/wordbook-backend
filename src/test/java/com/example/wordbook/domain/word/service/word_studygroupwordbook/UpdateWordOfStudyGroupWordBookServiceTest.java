package com.example.wordbook.domain.word.service.word_studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.word.controller.word_studygroupwordbook.WordOfStudyGroupWordBookControllerTest;
import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.service.studygroupwordbook.AddWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.GetWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.UpdateWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateWordOfStudyGroupWordBookServiceTest extends WordOfStudyGroupWordBookServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private GetWordOfStudyGroupWordBookService getWordOfStudyGroupWordBookService;

    @Test
    @DisplayName("정상적으로 그룹단어장에 단어를 수정하는 테스트")
    public void updateWordAtStudyGroupWordBook() {
        //given
        UpdateWordOfStudyGroupWordBookService updateWordOfStudyGroupWordBookService = new UpdateWordOfStudyGroupWordBookService(
                wordRepository,
                getWordOfStudyGroupWordBookService,
                wordOfStudyGroupWordBookToWordDetailDtoMapper);

        Study study = domainFactory.getStudyOfStudyGroupNormal();
        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        UpdateWordDTO updateWordDTO = UpdateWordDTO.builder()
                .voca("apple")
                .meaning("사과")
                .build();

        Word word = studyGroupWordBook.getWords().get(0);

        given(wordRepository.save(any(Word.class))).willReturn(word);
        given(getWordOfStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(anyLong(), anyLong(), anyLong(), anyLong()))
                .willReturn(word);

        //when
        Long user = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = studyGroupWordBook.getId();
        Long wordId = word.getId();

        WordDetailDTO wordDetailDTO = updateWordOfStudyGroupWordBookService.update(user, studyGroupId, wordBookId, wordId, updateWordDTO);

        //then
        assertThat(wordDetailDTO).isNotNull();
        assertThat(wordDetailDTO.getId()).isEqualTo(word.getId());
        assertThat(wordDetailDTO.getVoca()).isEqualTo(word.getVoca());
        assertThat(wordDetailDTO.getMeaning()).isEqualTo(word.getMeaning());
    }
}
