package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("스터디 그룹과 단어장이 있을 때 Body와 Created를 반환")
    void createUser() throws Exception {
        //given
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        UserDetailDTO userDetailDTO = getUserDetailResponseDTO();

        given(createUserService.create(any(CreateUserDTO.class))).willReturn(userDetailDTO);

        //when
        ResultActions resultActions = requestCreateUser(createUserDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists());

        urlExistCheck(resultActions, "wordBookDTOList[*]", DomainLink.GET_USER_WORDBOOK);
        urlExistCheck(resultActions, "studyGroupList[*]", DomainLink.GET_STUDY_GROUP);
        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_USER);
//        urlExistCheck(resultActions, LinkName.DELETE_USER);
        urlExistCheck(resultActions, DomainLink.CREATE_STUDY_GROUP);
        urlExistCheck(resultActions, DomainLink.CREATE_USER_WORDBOOK);
    }
}

//        for (Field field : UserDetailResponseDTO.class.getFields()) {
//            fieldExistCheck(resultActions, field.getName());
//        }