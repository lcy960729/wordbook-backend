package com.example.wordbook.domain.wordbook.controller.userwordbook;

import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UpdateUserWordBookControllerTest extends UserWordBookControllerTest {

    private ResultActions requestUpdateUserWordBook(Long userId, Long wordBookId, UpdateWordBookDTO updateWordBookDTO) throws Exception {
        return mockMvc.perform(
                put(linkTo(methodOn(UserWordBookController.class).update(userId, wordBookId, updateWordBookDTO)).toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 수정하는 테스트")
    public void updateUserWordBook_Test() throws Exception {
        //given
        UpdateWordBookDTO updateWordBookDTO = UpdateWordBookDTO
                .builder()
                .name("UpdateTestWordBook")
                .build();

        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail();
        wordBookDetailDTO.setName(updateWordBookDTO.getName());

        given(updateUserWordBookService.update_name(anyLong(),anyLong(), any(UpdateWordBookDTO.class)))
                .willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestUpdateUserWordBook(0L, wordBookDetailDTO.getId(), updateWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());

        fieldExistCheck(resultActions, "id");
        fieldExistCheck(resultActions, "name");
        fieldExistCheck(resultActions, "wordDTOList");

        urlExistCheck(resultActions, DomainLink.SELF);
        urlExistCheck(resultActions, DomainLink.UPDATE_USER_WORDBOOK);
        urlExistCheck(resultActions, DomainLink.DELETE_USER_WORDBOOK);
        urlExistCheck(resultActions, DomainLink.ADD_WORD);

        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.UPDATE_WORD);
        urlExistCheck(resultActions, "wordDTOList[*]", DomainLink.DELETE_WORD);

        urlExistCheck(resultActions, DomainLink.GET_USER);
    }
}