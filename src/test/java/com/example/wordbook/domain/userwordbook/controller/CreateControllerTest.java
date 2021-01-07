package com.example.wordbook.domain.userwordbook.controller;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.service.CreateUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.DeleteUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.GetUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.UpdateUserWordBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserWordBookController.class)
public class CreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetUserWordBookService getUserWordBookService;
    @MockBean
    private CreateUserWordBookService createUserWordBookService;
    @MockBean
    private UpdateUserWordBookService updateUserWordBookService;
    @MockBean
    private DeleteUserWordBookService deleteUserWordBookService;

    private final Long userIdThatIsValid = 0L;

    private ResultActions requestCreateUserWordBook(UserWordBookRequestDTO.Create createUserWordBookDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/userWordBooks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createUserWordBook() throws Exception {
        //given
        String nameConsistingWellFormed = "TestWordBook";
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create
                .builder()
                .name(nameConsistingWellFormed)
                .userId(userIdThatIsValid)
                .build();
        given(createUserWordBookService.create(any(UserWordBookRequestDTO.Create.class))).willReturn(0L);

        //when
        ResultActions resultActions = requestCreateUserWordBook(createUserWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createUserWordBook_Bad_Request_Empty_Input() throws Exception {
        //given
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create
                .builder()
                .build();

        //when
        ResultActions resultActions = requestCreateUserWordBook(createUserWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest());
        //        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createUserWordBook_Bad_Request_Wrong_Input() throws Exception {
        //given
        String nameConsistingOnlyOfNumbers = "1234";
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create
                .builder()
                .name(nameConsistingOnlyOfNumbers)
                .userId(userIdThatIsValid)
                .build();

        //when
        ResultActions resultActions = requestCreateUserWordBook(createUserWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].field").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$[0].rejectedValue").exists());
    }
}