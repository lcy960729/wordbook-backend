package com.example.wordbook.domain.wordbook.controller.studygroupwordbook;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


public class GetUserWordBookControllerTest extends StudyGroupWordBookControllerTest {

    private ResultActions requestGetStudyGroupWordBook(Long userId, Long studyGroupId, Long wordBookId) throws Exception {
        return mockMvc.perform(
                get("/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks/{wordBookId}", userId, studyGroupId, wordBookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("정상적으로 일반 권한을 가진 유저가 그룹 단어장을 반환하는 테스트")
    public void getStudyGroupWordBook_NormalRole_Test() throws Exception {
        //given

        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail_Normal();

        given(getStudyGroupWordBookService.getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong()))
                .willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestGetStudyGroupWordBook(0L, 1L, wordBookDetailDTO.getId());

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "name");
        fieldExistCheck(resultActions, "wordDTOList");

        urlExistCheck(resultActions, DomainLink.SELF);
        urlNotExistCheck(resultActions, DomainLink.UPDATE_STUDY_GROUP_WORDBOOK);
        urlNotExistCheck(resultActions, DomainLink.DELETE_STUDY_GROUP_WORDBOOK);

        urlExistCheck(resultActions, DomainLink.ADD_WORD);

        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.UPDATE_WORD);
        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.DELETE_WORD);

        urlExistCheck(resultActions, DomainLink.GET_STUDY_GROUP);
    }

    @Test
    @DisplayName("정상적으로 관리자 권한을 가진 유저가 그룹 단어장을 반환하는 테스트")
    public void getStudyGroupWordBook_AdminRole_Test() throws Exception {
        //given
        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail_Admin();

        given(getStudyGroupWordBookService.getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong()))
                .willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestGetStudyGroupWordBook(0L, 1L, wordBookDetailDTO.getId());

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "name");
        fieldExistCheck(resultActions, "wordDTOList");

        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_STUDY_GROUP_WORDBOOK);
        urlExistCheck(resultActions, DomainLink.DELETE_STUDY_GROUP_WORDBOOK);

        urlExistCheck(resultActions, DomainLink.ADD_WORD);

        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.UPDATE_WORD);
        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.DELETE_WORD);

        urlExistCheck(resultActions, DomainLink.GET_STUDY_GROUP);
    }
}