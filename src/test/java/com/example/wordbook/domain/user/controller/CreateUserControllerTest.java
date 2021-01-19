package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateUserControllerTest extends UserControllerTest {

    private ResultActions requestCreateUser(CreateUserDTO createUserDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 유저를 생성 Created 반환")
    void createUser() throws Exception {
        //given
        String name = "testName";
        String email = "testEmail@test.com";

        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .email(email)
                .name(name)
                .pw("testPw")
                .build();

        UserDetailDTO userDetailDTO = getUserDetailResponseDTO(name, email);

        given(createUserService.create(any(CreateUserDTO.class))).willReturn(userDetailDTO);

        //when
        ResultActions resultActions = requestCreateUser(createUserDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "name");
        fieldExistCheck(resultActions, "email");
        fieldExistCheck(resultActions, "wordBookDTOList");
        fieldExistCheck(resultActions, "studyGroupDTOList");

        urlExistCheck(resultActions, "wordBookDTOList[*]", DomainLink.GET_USER_WORDBOOK);
        urlExistCheck(resultActions, "studyGroupDTOList[*]", DomainLink.GET_STUDY_GROUP);

        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_USER);
        urlExistCheck(resultActions, DomainLink.DELETE_USER);
        urlExistCheck(resultActions, DomainLink.CREATE_STUDY_GROUP);
        urlExistCheck(resultActions, DomainLink.CREATE_USER_WORDBOOK);
    }
}

//        for (Field field : UserDetailResponseDTO.class.getFields()) {
//            fieldExistCheck(resultActions, field.getName());
//        }