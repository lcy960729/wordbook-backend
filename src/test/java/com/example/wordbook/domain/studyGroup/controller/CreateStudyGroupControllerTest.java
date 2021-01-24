package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.enums.StudyGroupRole;
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

class CreateStudyGroupControllerTest extends StudyGroupControllerTest {

    private ResultActions requestCreateStudyGroup(Long userId, CreateStudyGroupDTO createStudyGroupDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/{userId}/study-groups", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createStudyGroupDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 그룹이 생성 됐을때 Created를 반환")
    void create() throws Exception {
        //given
        String studyGroupName = "testName";

        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name(studyGroupName)
                .build();

        StudyGroupDetailDTO studyGroupDetailDTO = getStudyGroupDetailDTO(studyGroupName, StudyGroupRole.ADMIN);

        given(createStudyGroupService.create(anyLong(), any(CreateStudyGroupDTO.class))).willReturn(studyGroupDetailDTO);

        //when
        long userId = 0L;
        ResultActions resultActions = requestCreateStudyGroup(userId, createStudyGroupDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "name");
        fieldExistCheck(resultActions, "userDTOList");
        fieldExistCheck(resultActions, "wordBookDTOList");

//        urlExistCheck(resultActions, "userDTOList[*]", DomainLink.GET_USER);
        urlExistCheck(resultActions, "wordBookDTOList[*]", DomainLink.GET_STUDY_GROUP_WORDBOOK);

        urlExistCheck(resultActions, DomainLink.SELF);

        urlExistCheck(resultActions, DomainLink.UPDATE_STUDY_GROUP);
        urlExistCheck(resultActions, DomainLink.DELETE_STUDY_GROUP);
        urlExistCheck(resultActions, DomainLink.CREATE_STUDY_GROUP_WORDBOOK);

        urlExistCheck(resultActions, DomainLink.GET_USER);
    }
}