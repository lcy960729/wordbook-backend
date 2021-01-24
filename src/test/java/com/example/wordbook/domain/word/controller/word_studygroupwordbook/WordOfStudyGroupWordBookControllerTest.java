package com.example.wordbook.domain.word.controller.word_studygroupwordbook;


import com.example.wordbook.domain.word.controller.WordOfStudyGroupWordBookController;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.service.studygroupwordbook.AddWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.DeleteWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.GetWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.UpdateWordOfStudyGroupWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(controllers = WordOfStudyGroupWordBookController.class)
public class WordOfStudyGroupWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected AddWordOfStudyGroupWordBookService addWordOfStudyGroupWordBookService;

    @MockBean
    protected UpdateWordOfStudyGroupWordBookService updateWordOfStudyGroupWordBookService;

    @MockBean
    protected DeleteWordOfStudyGroupWordBookService deleteWordOfStudyGroupWordBookService;

    protected WordDetailDTO getWordBookDetailDTO(){
        WordDetailDTO wordDetailDTO = WordDetailDTO.builder()
                .id(0L)
                .voca("test")
                .meaning("테스트")
                .build();

        wordDetailDTO.makeLinks(0L, 2L, 3L, wordDetailDTO.getId());

        return wordDetailDTO;
    }

}
