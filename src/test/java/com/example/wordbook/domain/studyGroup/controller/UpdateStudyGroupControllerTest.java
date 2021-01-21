package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.request.UpdateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.enums.StudyGroupRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateStudyGroupControllerTest extends StudyGroupControllerTest {

    private ResultActions requestUpdateStudyGroup(Long userId, Long studyGroupId, UpdateStudyGroupDTO updateStudyGroupDTO) throws Exception {
        return mockMvc.perform(
                put("/api/v1/users/{userId}/study-groups/{studyGroupId}", userId, studyGroupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateStudyGroupDTO))
        );
    }

    @Test
    @DisplayName("조회 요청한 사용자가 그룹 관리자일 경우 그룹 정보를 반환하는 테스트")
    void getStudyGroup_userIsAdmin_test() throws Exception {
        //given
        Long userId = 0L;
        Long studyGroupId = 0L;

        UpdateStudyGroupDTO updateStudyGroupDTO = new UpdateStudyGroupDTO("updateName");

        StudyGroupDetailDTO studyGroupDetailDTO = getStudyGroupDetailDTO(updateStudyGroupDTO.getName(), StudyGroupRole.ADMIN);

        given(updateStudyGroupService.update(anyLong(), anyLong(), any(UpdateStudyGroupDTO.class))).willReturn(studyGroupDetailDTO);

        //when
        ResultActions resultActions = requestUpdateStudyGroup(userId, studyGroupId, updateStudyGroupDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());

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