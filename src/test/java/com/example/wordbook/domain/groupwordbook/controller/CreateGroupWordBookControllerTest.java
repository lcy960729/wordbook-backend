package com.example.wordbook.domain.groupwordbook.controller;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.service.CreateGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.DeleteGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.GetGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.UpdateGroupWordBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class CreateGroupWordBookControllerTest extends GroupWordBookControllerTest {

    private final Long groupIdThatIsValid = 0L;

    @BeforeAll
    static void setUp() {

    }

    private ResultActions requestCreateGroupWordBook(GroupWordBookRequestDTO.Create createGroupWordBookDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/groupWordBooks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createGroupWordBookDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createGroupWordBook() throws Exception {
        //given
        String nameConsistingWellFormed = "TestWordBook";
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create
                .builder()
                .name(nameConsistingWellFormed)
                .groupId(groupIdThatIsValid)
                .build();

        given(createGroupWordBookService.create(any(GroupWordBookRequestDTO.Create.class))).willReturn(0L);

        //when
        ResultActions resultActions = requestCreateGroupWordBook(createGroupWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createGroupWordBook_Bad_Request_Empty_Input() throws Exception {
        //given
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create
                .builder()
                .build();

        //when
        ResultActions resultActions = requestCreateGroupWordBook(createGroupWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest());
        //        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createGroupWordBook_Bad_Request_Wrong_Input() throws Exception {
        //given
        String nameConsistingOnlyOfNumbers = "1234";
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create
                .builder()
                .name(nameConsistingOnlyOfNumbers)
                .groupId(groupIdThatIsValid)
                .build();

        //when
        ResultActions resultActions = requestCreateGroupWordBook(createGroupWordBookDTO);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
//                .andExpect(header().(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].field").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$[0].rejectedValue").exists());
    }
}