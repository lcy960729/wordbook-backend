package com.example.wordbook.domain.word.controller.word_userwordbook;


import com.example.wordbook.domain.word.controller.WordOfUserWordBookController;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.service.userwordbook.AddWordOfUserWordBookService;
import com.example.wordbook.domain.word.service.userwordbook.DeleteWordOfUserWordBookService;
import com.example.wordbook.domain.word.service.userwordbook.UpdateWordOfUserWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(controllers = WordOfUserWordBookController.class)
public class WordOfUserWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected AddWordOfUserWordBookService addWordOfUserWordBookService;

    @MockBean
    protected UpdateWordOfUserWordBookService updateWordOfUserWordBookService;

    @MockBean
    protected DeleteWordOfUserWordBookService deleteWordOfUserWordBookService;

    protected WordDetailDTO getWordBookDetailDTO() {
        WordDetailDTO wordDetailDTO = WordDetailDTO.builder()
                .id(0L)
                .voca("test")
                .meaning("테스트")
                .build();

        wordDetailDTO.makeLinks(0L, 1L, wordDetailDTO.getId());

        return wordDetailDTO;
    }

}
