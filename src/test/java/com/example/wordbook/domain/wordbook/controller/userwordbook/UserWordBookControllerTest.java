package com.example.wordbook.domain.wordbook.controller.userwordbook;


import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.CreateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.DeleteStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.UpdateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.CreateUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.DeleteUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.UpdateUserWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@WebMvcTest(controllers = UserWordBookController.class)
public class UserWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateUserWordBookService createUserWordBookService;

    @MockBean
    protected UpdateUserWordBookService updateUserWordBookService;

    @MockBean
    protected GetUserWordBookService getUserWordBookService;

    @MockBean
    protected DeleteUserWordBookService deleteUserWordBookService;

    protected WordBookDetailDTO getWordBookDetail() {
        List<WordBookDetailDTO.WordDTO> wordDTOList = new ArrayList<>();
        LongStream.range(0, 4).forEach(i -> {
            wordDTOList.add(new WordBookDetailDTO.WordDTO(i, "testVoca" + i, "testMeaning" + i));
        });

        WordBookDetailDTO wordBookDetailDTO = WordBookDetailDTO.builder()
                .id(0L)
                .isUsing(true)
                .name("testWordBookName")
                .wordDTOList(wordDTOList)
                .build();

        wordBookDetailDTO.makeLinks(0L);

        return wordBookDetailDTO;

    }
}