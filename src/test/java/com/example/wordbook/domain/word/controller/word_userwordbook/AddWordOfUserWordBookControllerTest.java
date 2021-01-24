package com.example.wordbook.domain.word.controller.word_userwordbook;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddWordOfUserWordBookControllerTest extends WordOfUserWordBookControllerTest {

    private ResultActions createWordRequest(Long userId, Long wordBookId, CreateWordDTO createWordDTO) throws Exception {
        return mockMvc.perform(
                post(DomainLink.WordOfUserWordBook.add(userId, wordBookId).toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createWordDTO))
        );
    }


    @Test
    @DisplayName("정상적으로 개인 단어장에 단어를 추가하는 테스트")
    public void addWordAtUserWordBook() throws Exception {
        //given
        CreateWordDTO createWordDTO = CreateWordDTO.builder()
                .voca("test")
                .meaning("테스트")
                .build();

        WordDetailDTO wordDetailDTO = getWordBookDetailDTO();
        wordDetailDTO.setVoca(createWordDTO.getVoca());
        wordDetailDTO.setMeaning(createWordDTO.getMeaning());

        given(addWordOfUserWordBookService.add(anyLong(), anyLong(), any(CreateWordDTO.class)))
                .willReturn(wordDetailDTO);

        //when
        long userId = 0L;
        long wordBookId = 2L;

        ResultActions resultActions = createWordRequest(userId, wordBookId, createWordDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "voca");
        fieldExistCheck(resultActions, "meaning");

        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_WORD);
        urlExistCheck(resultActions, DomainLink.DELETE_WORD);

        urlExistCheck(resultActions, DomainLink.GET_USER_WORDBOOK);
    }
}
