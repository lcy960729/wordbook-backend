package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.enums.StudyGroupRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetStudyGroupControllerTest extends StudyGroupControllerTest {

    private ResultActions requestGetStudyGroup(Long userId, Long studyGroupId) throws Exception {
        return mockMvc.perform(
                get("/api/v1/users/{userId}/study-groups/{studyGroupId}" , userId, studyGroupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("조회 요청한 사용자가 그룹 관리자일 경우 그룹 정보를 반환하는 테스트")
    void getStudyGroup_userIsAdmin_test() throws Exception {
        //given
        Long userId = 0L;
        Long studyGroupId = 0L;

        StudyGroupDetailDTO studyGroupDetailDTO = getStudyGroupDetailDTO("testName", StudyGroupRole.ADMIN);

        given(getStudyGroupService.getDetailDTOByUserIdAndStudyGroupId(anyLong(), anyLong())).willReturn(studyGroupDetailDTO);

        //when
        ResultActions resultActions = requestGetStudyGroup(userId, studyGroupId);

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

    @Test
    @DisplayName("조회 요청한 사용자가 그룹원일 경우 그룹 정보를 반환하는 테스트")
    void getStudyGroup_userIsNormal_test() throws Exception {
        //given
        Long userId = 0L;
        Long studyGroupId = 0L;

        StudyGroupDetailDTO studyGroupDetailDTO = getStudyGroupDetailDTO("testName", StudyGroupRole.NORMAL);

        given(getStudyGroupService.getDetailDTOByUserIdAndStudyGroupId(anyLong(), anyLong())).willReturn(studyGroupDetailDTO);

        //when
        ResultActions resultActions = requestGetStudyGroup(userId, studyGroupId);

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


        urlExistCheck(resultActions, DomainLink.GET_USER);

    }

}