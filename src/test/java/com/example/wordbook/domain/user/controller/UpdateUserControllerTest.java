package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateUserControllerTest extends UserControllerTest {

    private ResultActions requestUpdateUser(Long id, UpdateUserDTO userUpdateDTO) throws Exception {
        return mockMvc.perform(
                put("/api/v1/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 유저 정보를 업데이트 하는 테스트")
    void update() throws Exception {
        //given
        String updateName = "updateName";

        UpdateUserDTO userUpdateDTO = UpdateUserDTO.builder()
                .name(updateName)
                .build();

        UserDetailDTO userDetailDTO = getUserDetailResponseDTO(updateName, "testEmail@test.com");

        given(updateUserService.update(anyLong(), any())).willReturn(userDetailDTO);

        //when
        ResultActions resultActions = requestUpdateUser(userDetailDTO.getId(), userUpdateDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());

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