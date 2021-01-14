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
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserGetControllerTest extends UserControllerTest {

    private ResultActions requestGetUser(Long id) throws Exception {
        return mockMvc.perform(
                get("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("정상적으로 유저를 불러오는 테스트")
    void getUser() throws Exception {
        //given
        UserDetailResponseDTO userDetailResponseDTO = getUserDetailResponseDTO();

        given(getUserService.getDetailDTOById(anyLong())).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestGetUser(userDetailResponseDTO.getId());

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