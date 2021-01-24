package com.example.wordbook.domain.word.service.word_studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.exception.NotFoundWordException;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.service.studygroupwordbook.GetWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.UpdateWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class GetWordOfStudyGroupWordBookServiceTest extends WordOfStudyGroupWordBookServiceTest {

    @Mock
    private GetStudyGroupWordBookService getStudyGroupWordBookService;

    @Test
    @DisplayName("정상적으로 그룹단어장에 단어를 반환하는 테스트")
    public void getWordAtStudyGroupWordBook() {
        //given
        GetWordOfStudyGroupWordBookService getWordOfStudyGroupWordBookService = new GetWordOfStudyGroupWordBookService(getStudyGroupWordBookService);

        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();
        Word _word = studyGroupWordBook.getWords().get(0);

        given(getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong()))
                .willReturn(studyGroupWordBook);

        //when
        long wordId = _word.getId();
        long wordBookId = studyGroupWordBook.getId();
        long userId = 0L;
        long studyGroupId = 1L;
        Word word = getWordOfStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(userId, studyGroupId, wordBookId, wordId);

        //then
        assertThat(word).isNotNull();
        assertThat(word.getId()).isEqualTo(_word.getId());
        assertThat(word.getVoca()).isEqualTo(_word.getVoca());
        assertThat(word.getMeaning()).isEqualTo(_word.getMeaning());
    }

    @Test
    @DisplayName("유효하지 않은 단어를 그룹단어장에서 조회시 에러 반환 테스트")
    public void getWordAtStudyGroupWordBook_NotInvalidWord_ErrorTest() {
        //given
        GetWordOfStudyGroupWordBookService getWordOfStudyGroupWordBookService = new GetWordOfStudyGroupWordBookService(getStudyGroupWordBookService);

        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();
        studyGroupWordBook.getWords().clear();

        given(getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong()))
                .willReturn(studyGroupWordBook);

        //when
        long wordId = 0L;
        long wordBookId = studyGroupWordBook.getId();
        long userId = 0L;
        long studyGroupId = 1L;
        Throwable throwable = catchThrowable(() ->
                getWordOfStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(userId, studyGroupId, wordBookId, wordId)
        );

        //then
        assertThat(throwable).isInstanceOf(NotFoundWordException.class);
    }
}
