package com.example.wordbook.domain.userwordbook.controller;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.service.CreateUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.DeleteUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.GetUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.UpdateUserWordBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UpdateUserWordBookControllerTest extends UserWordBookControllerTest {

    private ResultActions requestUpdateUserWordBook(Long id) throws Exception {
        UserWordBookRequestDTO.Update updateDTO = UserWordBookRequestDTO.Update.builder()
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
        UserWordBookResponseDTO.Detail userWordBookDTO = UserWordBookResponseDTO.Detail.builder()
                .id(0L)
                .isUsing(true)
                .name("Cy의 단어장-후")
                .userId(0L)
                .build();

        given(updateUserWordBookService.update_name(anyLong(), any(UserWordBookRequestDTO.Update.class))).willReturn(userWordBookDTO);

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