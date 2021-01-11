package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCreateControllerTest extends UserControllerTest {

    private ResultActions requestCreateUser(CreateUserDTO createUserDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 유저가 추가 되었을때 Created를 반환")
    void create() throws Exception {
        //given
        CreateUserDTO userCreateDTOConsistingWellFormedInput = CreateUserDTO.builder()
                .email("testId")
                .name("testName")
                .pw("testPw")
                .build();

        given(createUserService.create(any(CreateUserDTO.class))).willReturn(0L);

        //when
        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    @DisplayName("유저가 추가가 잘못된 입력값에 의해 실패되었을때 404를 반")
    void create_ErrorTest() throws Exception {
        //given
        CreateUserDTO userCreateDTOConsistingWellFormedInput = CreateUserDTO.builder()
                .email(null)
                .name(null)
                .pw(null)
                .build();

        given(createUserService.create(any(CreateUserDTO.class))).willReturn(0L);

        //when
        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);

        //then
        resultActions.andDo(print())
                .andExpect(status().isBadRequest());
    }
}