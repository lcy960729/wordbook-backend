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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateStudyGroupWordBookControllerTest extends StudyGroupWordBookControllerTest {

    private ResultActions requestCreateStudyGroupWordBook(Long userId, Long studyGroupId, CreateWordBookDTO createUserWordBookDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks", userId, studyGroupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 관리자 권한을 가진 유저가 그룹 단어장을 생성하는 테스트")
    public void createStudyGroupWordBook_AdminRole_Test() throws Exception {
        //given
        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO
                .builder()
                .name("testWordBook")
                .build();

        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail_Admin();
        wordBookDetailDTO.setName(createWordBookDTO.getName());

        given(createStudyGroupWordBookService.create(anyLong(), anyLong(), any(CreateWordBookDTO.class))).willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestCreateStudyGroupWordBook(0L, 0L, createWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));

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