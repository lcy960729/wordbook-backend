package com.example.wordbook.domain.word.controller.word_studygroupwordbook;

import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateWordOfStudyGroupWordBookControllerTest extends WordOfStudyGroupWordBookControllerTest{

    private ResultActions updateWordRequest(Long userId, Long studyGroupId, Long wordBookId, Long wordId, UpdateWordDTO updateWordDTO) throws Exception {
        return mockMvc.perform(
                put(DomainLink.WordOfStudyGroupWordBook.update(userId, studyGroupId, wordBookId, wordId).toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateWordDTO))
        );
    }


    @Test
    @DisplayName("정상적으로 그룹 단어장에 단어를 수정하는 테스트")
    public void updateWordAtStudyGroupWordBook() throws Exception {
        //given
        UpdateWordDTO updateWordDTO = UpdateWordDTO.builder()
                .voca("apple")
                .meaning("사과")
                .build();

        WordDetailDTO wordDetailDTO = getWordBookDetailDTO();
        wordDetailDTO.setVoca(updateWordDTO.getVoca());
        wordDetailDTO.setMeaning(updateWordDTO.getMeaning());

        given(updateWordOfStudyGroupWordBookService.update(anyLong(), anyLong(), anyLong(), anyLong(), any(UpdateWordDTO.class)))
                .willReturn(wordDetailDTO);

        //when
        long userId = 0L;
        long studyGroupId = 1L;
        long wordBookId = 2L;
        long wordId = 3L;

        ResultActions resultActions = updateWordRequest(userId, studyGroupId, wordBookId, wordId, updateWordDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "voca");
        fieldExistCheck(resultActions, "meaning");

        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_WORD);
        urlExistCheck(resultActions, DomainLink.DELETE_WORD);

        urlExistCheck(resultActions, DomainLink.GET_STUDY_GROUP_WORDBOOK);
    }
}
