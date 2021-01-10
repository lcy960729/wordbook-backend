package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import javax.validation.Valid;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateWordBookControllerTest extends WordBookControllerTest {

    @MockBean
    private CreateWordBookService createWordBookService;

    private final Long userIdThatIsValid = 0L;

    private ResultActions requestCreateWordBook(WordBookRequestDTO.Create createUserWordBookDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/wordBooks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createUserWordBook() throws Exception {
        //given
        String nameConsistingWellFormed = "TestWordBook";
        WordBookRequestDTO.Create createWordBookDTO = WordBookRequestDTO.Create
                .builder()
                .name(nameConsistingWellFormed)
                .wordBookType(WordBookType.USER)
                .ownerId(userIdThatIsValid)
                .build();

        given(wordBookServiceFactory.getCreateServiceImpl(any(WordBookType.class))).willReturn(createWordBookService);
        given(createWordBookService.create(any(WordBookRequestDTO.Create.class))).willReturn(0L);

        //when
        ResultActions resultActions = requestCreateWordBook(createWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createUserWordBook_Bad_Request_Empty_Input() throws Exception {
        //given
        WordBookRequestDTO.Create createUserWordBookDTO = WordBookRequestDTO.Create
                .builder()
                .build();

        //when
        ResultActions resultActions = requestCreateWordBook(createUserWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest());
        //        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createUserWordBook_Bad_Request_Wrong_Input() throws Exception {
        //given
        String nameConsistingOnlyOfNumbers = "1234";
        WordBookRequestDTO.Create createUserWordBookDTO = WordBookRequestDTO.Create
                .builder()
                .name(nameConsistingOnlyOfNumbers)
                .ownerId(userIdThatIsValid)
                .build();

        //when
        ResultActions resultActions = requestCreateWordBook(createUserWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].field").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$[0].rejectedValue").exists());
    }
}