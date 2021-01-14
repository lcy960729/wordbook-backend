package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudyGroupGetControllerTest extends StudyGroupControllerTest {

    private ResultActions requestGetUser(Long userId, Long studyGroupId) throws Exception {
        return mockMvc.perform(
                get("/api/v1/users/{userId}/study-groups/{studyGroupId}" , userId, studyGroupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("조회 요청한 사용자가 그룹일 경우 그룹 정보를 반환하는 테스트")
    void getStudyGroup_userIsAdmin_test() throws Exception {
        //given
        Long userId = 0L;
        Long studyGroupId = 0L;

        StudyGroupDetailResponseDTO studyGroupDetailResponseDTO = StudyGroupDetailResponseDTO.builder()
                .id(studyGroupId)
                .name("testName")
                .build();

        studyGroupDetailResponseDTO.setWordBookList(new ArrayList<>());
        studyGroupDetailResponseDTO.setUserList(new ArrayList<>());
        studyGroupDetailResponseDTO.makeLinks(userId, StudyGroupRole.ADMIN);

        given(getStudyGroupService.getDetailDTOById(anyLong(), anyLong())).willReturn(studyGroupDetailResponseDTO);

        //when
        ResultActions resultActions = requestGetUser(userId, studyGroupId);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("userList").exists())
                .andExpect(jsonPath("wordBookList").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_studyGroup").exists())
                .andExpect(jsonPath("_links.delete_studyGroup").exists())
                .andExpect(jsonPath("_links.pre").exists());
    }

    @Test
    @DisplayName("조회 요청한 사용자가 그룹원일 경우 그룹 정보를 반환하는 테스트")
    void getStudyGroup_userIsNormal_test() throws Exception {
        //given
        Long userId = 0L;
        Long studyGroupId = 0L;

        StudyGroupDetailResponseDTO studyGroupDetailResponseDTO = StudyGroupDetailResponseDTO.builder()
                .id(studyGroupId)
                .name("testName")
                .build();

        studyGroupDetailResponseDTO.setWordBookList(new ArrayList<>());
        studyGroupDetailResponseDTO.setUserList(new ArrayList<>());
        studyGroupDetailResponseDTO.makeLinks(userId, StudyGroupRole.NORMAL);

        given(getStudyGroupService.getDetailDTOById(anyLong(), anyLong())).willReturn(studyGroupDetailResponseDTO);

        //when
        ResultActions resultActions = requestGetUser(userId, studyGroupId);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("userList").exists())
                .andExpect(jsonPath("wordBookList").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.pre").exists());
    }

}