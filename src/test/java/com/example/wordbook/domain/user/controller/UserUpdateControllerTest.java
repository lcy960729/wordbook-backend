package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.UserDetailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserUpdateControllerTest extends UserControllerTest {

    private ResultActions requestUpdateUser(Long id, UpdateUserDTO userUpdateDTO) throws Exception {
        return mockMvc.perform(
                put("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDTO))
        );
    }

    @Test
    @DisplayName("정상적인 입력에 대해 유저 정보 업데이트 성공 테스트")
    void update() throws Exception {
        //given
        Long id = 0L;

        UpdateUserDTO userUpdateDTO = UpdateUserDTO.builder()
                .name("testName")
                .build();

        UserDetailDTO userDetailDTO = UserDetailDTO.builder()
                .id(id)
                .name(userUpdateDTO.getName())
                .build();

        given(updateUserService.update(anyLong(), any())).willReturn(userDetailDTO);

        //when
        ResultActions resultActions = requestUpdateUser(id, userUpdateDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }
}