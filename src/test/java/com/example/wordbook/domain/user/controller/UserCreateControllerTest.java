package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserCreateControllerTest extends UserControllerTest {

    private ResultActions requestCreateUser(CreateUserRequestDTO createUserRequestDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 유저가 추가 되었을때 Created를 반환")
    void create() throws Exception {
        //given
        CreateUserRequestDTO userCreateDTOConsistingWellFormedInput = CreateUserRequestDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(0L)
                .name("testName")
                .email("testEmail")
                .userWordBookList(new ArrayList<>())
                .build();
        given(createUserService.create(any(CreateUserRequestDTO.class))).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_user").exists())
                .andExpect(jsonPath("_links.delete_user").exists())
                .andExpect(jsonPath("_links.get_studyGroup").exists())
                .andExpect(jsonPath("_links.get_wordBook").exists());
    }

//    @Test
//    @DisplayName("유저가 추가가 잘못된 입력값에 의해 실패되었을때 404를 반")
//    void create_ErrorTest() throws Exception {
//        //given
//        CreateUserDTO userCreateDTOConsistingWellFormedInput = CreateUserDTO.builder()
//                .email(null)
//                .name(null)
//                .pw(null)
//                .build();
//
//        given(createUserService.create(any(CreateUserDTO.class))).willReturn(0L);
//
//        //when
//        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);
//
//        //then
//        resultActions.andDo(print())
//                .andExpect(status().isBadRequest());
//    }
}