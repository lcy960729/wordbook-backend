package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.global.component.LinkName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.LongStream;

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
    @DisplayName("스터디 그룹과 단어장이 있을 때 Body와 Created를 반환")
    void createUser() throws Exception {
        //given
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        UserDetailResponseDTO userDetailResponseDTO = getUserDetailResponseDTO();

        given(createUserService.create(any(CreateUserRequestDTO.class))).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestCreateUser(createUserRequestDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists());

        urlExistCheck(resultActions, "wordBookDTOList[*]", LinkName.GET_USER_WORDBOOK);
        urlExistCheck(resultActions, "studyGroupList[*]", LinkName.GET_STUDY_GROUP);
        urlExistCheck(resultActions, LinkName.SELF);
        urlExistCheck(resultActions, LinkName.UPDATE_USER);
//        urlExistCheck(resultActions, LinkName.DELETE_USER);
        urlExistCheck(resultActions, LinkName.CREATE_STUDY_GROUP);
        urlExistCheck(resultActions, LinkName.CREATE_USER_WORDBOOK);
    }
}

//        for (Field field : UserDetailResponseDTO.class.getFields()) {
//            fieldExistCheck(resultActions, field.getName());
//        }