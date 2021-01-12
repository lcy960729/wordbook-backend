package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.UpdateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
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

    private ResultActions requestUpdateUser(Long id, UpdateUserRequestDTO userUpdateDTO) throws Exception {
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

        UpdateUserRequestDTO userUpdateDTO = UpdateUserRequestDTO.builder()
                .name("testName")
                .build();

        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(id)
                .name(userUpdateDTO.getName())
                .build();

        given(updateUserService.update(anyLong(), any())).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestUpdateUser(id, userUpdateDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }
}