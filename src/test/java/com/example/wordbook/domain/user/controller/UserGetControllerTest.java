package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.UserDetailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Long id = 0L;
        UserDetailDTO detailUserResponseDTO = UserDetailDTO.builder()
                .id(id)
                .name("testUser")
                .build();

        given(getUserService.getDetailDTOById(id)).willReturn(detailUserResponseDTO);

        //when
        ResultActions resultActions = requestGetUser(id);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("눌값 입력시 실패하는 테스트")
    void getUser_ErrorTest_NullInput() throws Exception {
        //given
        Long id = 0L;
        UserDetailDTO detailUserResponseDTO = UserDetailDTO.builder()
                .id(id)
                .name("testUser")
                .build();

        //when
        ResultActions resultActions = requestGetUser(id);
        given(getUserService.getDetailDTOById(id)).willReturn(detailUserResponseDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}