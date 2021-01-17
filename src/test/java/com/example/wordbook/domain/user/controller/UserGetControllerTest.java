package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

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
        UserDetailDTO userDetailDTO = getUserDetailResponseDTO();

        given(getUserService.getDetailDTOById(anyLong())).willReturn(userDetailDTO);

        //when
        ResultActions resultActions = requestGetUser(userDetailDTO.getId());

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