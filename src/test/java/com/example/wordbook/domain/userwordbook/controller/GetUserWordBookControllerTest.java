package com.example.wordbook.domain.userwordbook.controller;

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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetUserWordBookControllerTest extends UserWordBookControllerTest{

    private ResultActions requestGetUserWordBook(Long id) throws Exception {
        return mockMvc.perform(
                get("/api/v1/userWordBooks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("정상적으로 id에 해당하는 단어장을 반환하는 테스트")
    public void getUserWordBook() throws Exception {
        //given
        UserWordBookResponseDTO.Detail userWordBook = UserWordBookResponseDTO.Detail.builder()
                .isUsing(true)
                .name("Cy의 단어장")
                .userId(0L)
                .build();
        userWordBook.setId(0L);

        given(getUserWordBookService.getDetailDTOById(anyLong())).willReturn(userWordBook);

        //when
        ResultActions resultActions = requestGetUserWordBook(0L);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("isUsing").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("userId").exists());
    }
}