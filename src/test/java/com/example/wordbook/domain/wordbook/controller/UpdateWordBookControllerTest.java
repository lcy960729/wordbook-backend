package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UpdateWordBookControllerTest extends WordBookControllerTest {

    private ResultActions requestUpdateUserWordBook(Long id) throws Exception {
        WordBookRequestDTO.Update updateDTO = WordBookRequestDTO.Update.builder()
                .id(0L)
                .name("Cy의 단어장-전")
                .build();

        return mockMvc.perform(
                put("/api/v1/userWordBooks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 id에 해당하는 단어장을 반환하는 테스트")
    public void updateUserWordBook() throws Exception {
        //given
        WordBookResponseDTO.Detail userWordBookDTO = WordBookResponseDTO.Detail.builder()
                .id(0L)
                .isUsing(true)
                .name("Cy의 단어장-후")
                .ownerId(0L)
                .build();

//        given(updateWordBookService.update_name(anyLong(), any(WordBookRequestDTO.Update.class))).willReturn(userWordBookDTO);

        //when
        ResultActions resultActions = requestUpdateUserWordBook(0L);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("isUsing").exists())
                .andExpect(jsonPath("name").value(userWordBookDTO.getName()))
                .andExpect(jsonPath("userId").exists());
    }
}