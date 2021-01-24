package com.example.wordbook.domain.wordbook.controller.studygroupwordbook;


import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.CreateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.DeleteStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.UpdateStudyGroupWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@WebMvcTest(controllers = StudyGroupWordBookController.class)
public class StudyGroupWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateStudyGroupWordBookService createStudyGroupWordBookService;

    @MockBean
    protected UpdateStudyGroupWordBookService updateStudyGroupWordBookService;

    @MockBean
    protected GetStudyGroupWordBookService getStudyGroupWordBookService;

    @MockBean
    protected DeleteStudyGroupWordBookService deleteStudyGroupWordBookService;

    private WordBookDetailDTO getWordBookDetail() {
        List<WordBookDetailDTO.WordDTO> wordDTOList = new ArrayList<>();
        LongStream.range(0, 4).forEach(i -> {
            wordDTOList.add(new WordBookDetailDTO.WordDTO(i, "testVoca" + i, "testMeaning" + i));
        });

        return WordBookDetailDTO.builder()
                .id(0L)
                .isUsing(true)
                .name("testWordBookName")
                .wordDTOList(wordDTOList)
                .build();
    }

    protected WordBookDetailDTO getWordBookDetail_Admin() {
        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail();
        wordBookDetailDTO.makeLinks(0L, 1L, true);
        return wordBookDetailDTO;
    }


    protected WordBookDetailDTO getWordBookDetail_Normal() {
        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail();
        wordBookDetailDTO.makeLinks(0L, 1L, false);
        return wordBookDetailDTO;
    }

}