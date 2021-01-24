package com.example.wordbook.domain.wordbook.controller.userwordbook;

import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetUserWordBookControllerTest extends UserWordBookControllerTest {

    private ResultActions requestGetUserWordBook(Long userId, Long wordBookId) throws Exception {
        return mockMvc.perform(
                get(linkTo(methodOn(UserWordBookController.class).get(userId, wordBookId)).toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("정상적으로 개인 단어장을 반환하는 테스트")
    public void getUserWordBook_Test() throws Exception {
        //given
        WordBookDetailDTO wordBookDetailDTO = getWordBookDetail();

        given(getUserWordBookService.getDetailDTOByUserIdAndWordBookId(anyLong(), anyLong()))
                .willReturn(wordBookDetailDTO);

        //when
        ResultActions resultActions = requestGetUserWordBook(0L, wordBookDetailDTO.getId());

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