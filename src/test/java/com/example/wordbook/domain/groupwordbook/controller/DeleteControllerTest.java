package com.example.wordbook.domain.groupwordbook.controller;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.groupwordbook.service.CreateGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.DeleteGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.GetGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.UpdateGroupWordBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GroupWordBookController.class)
public class DeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetGroupWordBookService getGroupWordBookService;
    @MockBean
    private CreateGroupWordBookService createGroupWordBookService;
    @MockBean
    private UpdateGroupWordBookService updateGroupWordBookService;
    @MockBean
    private DeleteGroupWordBookService deleteGroupWordBookService;

    private ResultActions requestDeleteGroupWordBook(Long id) throws Exception {
        return mockMvc.perform(
                delete("/api/v1/groupWordBooks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    @DisplayName("정상적으로 id에 해당하는 단어장을 삭제하는 테스트")
    public void deleteGroupWordBook() throws Exception {
        //given
        GroupWordBookResponseDTO.Detail groupWordBook = GroupWordBookResponseDTO.Detail.builder()
                .isUsing(true)
                .name("Cy의 단어장")
                .groupId(0L)
                .build();
        groupWordBook.setId(0L);

        //when
        ResultActions resultActions = requestDeleteGroupWordBook(0L);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }
}