package com.example.wordbook.domain.wordbook.controller.studygroupwordbook;

import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteStudyGroupWordBookControllerTest extends StudyGroupWordBookControllerTest {
//
//    private ResultActions requestDeleteUserWordBook(Long id) throws Exception {
//        return mockMvc.perform(
//                delete("/api/v1/userWordBooks/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaTypes.HAL_JSON)
//        );
//    }
//
//    @Test
//    @DisplayName("정상적으로 id에 해당하는 단어장을 삭제하는 테스트")
//    public void deleteUserWordBook() throws Exception {
//        //given
//        WordBookDetailDTO userWordBook = WordBookDetailDTO.builder()
//                .isUsing(true)
//                .name("Cy의 단어장")
//                .build();
//        userWordBook.setId(0L);
//
//        //when
//        ResultActions resultActions = requestDeleteUserWordBook(0L);
//
//        //then
//        resultActions
//                .andDo(print())
//                .andExpect(status().isOk());
//                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
//    }
}