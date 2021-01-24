package com.example.wordbook.domain.wordbook.controller.studygroupwordbook;

import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UpdateUserWordBookControllerTest extends StudyGroupWordBookControllerTest {

    private ResultActions requestUpdateStudyGroupWordBook(Long userId, Long studyGroupId, Long wordBookId, UpdateWordBookDTO updateWordBookDTO) throws Exception {
        return mockMvc.perform(
                put("/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks/{wordBookId}", userId, studyGroupId, wordBookId, updateWordBookDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 관리자 권한을 가진 유저가 그룹 단어장을 수정하는 테스트")
    public void updateStudyGroupWordBook_AdminRole_Test() throws Exception {
        //given
        UpdateWordBookDTO updateWordBookDTO = UpdateWordBookDTO.builder()
                .name("testWordBook")
                .build();

        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail_Admin();
        wordBookDetailDTO.setName(updateWordBookDTO.getName());

        given(updateStudyGroupWordBookService.update_name(anyLong(), anyLong(), anyLong(), any(UpdateWordBookDTO.class)))
                .willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestUpdateStudyGroupWordBook(0L, 0L, wordBookDetailDTO.getId(), updateWordBookDTO);

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