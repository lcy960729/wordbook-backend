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
        Long userId = 0L;

        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(userId)
                .name("testName")
                .email("testEmail")
                .wordBookDTOList(new ArrayList<>())
                .build();

        given(getUserService.getDetailDTOById(userId)).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestGetUser(userId);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_user").exists())
                .andExpect(jsonPath("_links.delete_user").exists())
                .andExpect(jsonPath("_links.get_studyGroup").exists())
                .andExpect(jsonPath("_links.get_wordBook").exists());
    }
}