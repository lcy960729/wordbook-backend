package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudyGroupCreateControllerTest extends StudyGroupControllerTest {

    private ResultActions requestCreateStudyGroup(Long userId, CreateStudyGroupDTO createStudyGroupDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/{userId}/study-groups", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createStudyGroupDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 유저가 추가 되었을때 Created를 반환")
    void create() throws Exception {
        //given
        long userId = 0L;
        String groupName = "testName";

        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name(groupName)
                .build();

        StudyGroupDetailDTO studyGroupDetailDTO = StudyGroupDetailDTO.builder()
                .id(0L)
                .name(groupName)
                .build();
        given(createStudyGroupService.create(anyLong(), any(CreateStudyGroupDTO.class))).willReturn(studyGroupDetailDTO);

        //when
        ResultActions resultActions = requestCreateStudyGroup(userId, createStudyGroupDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_studyGroup").exists())
                .andExpect(jsonPath("_links.delete_studyGroup").exists())
                .andExpect(jsonPath("_links.get_wordBooksInGroups").exists())
                .andExpect(jsonPath("_links.get_userInGroups").exists())
                .andExpect(jsonPath("_links.pre").exists());
    }
}