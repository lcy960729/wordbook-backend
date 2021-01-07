package com.example.wordbook.domain.groupwordbook.controller;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GroupWordBookController.class)
public class UpdateControllerTest {

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

    private ResultActions requestUpdateGroupWordBook(Long id) throws Exception {
        GroupWordBookRequestDTO.Update updateDTO = GroupWordBookRequestDTO.Update.builder()
                .id(0L)
                .name("Cy의 단어장-전")
                .build();

        return mockMvc.perform(
                put("/api/v1/groupWordBooks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO))
        );
    }

    @Test
    @DisplayName("정상적으로 id에 해당하는 단어장을 반환하는 테스트")
    public void updateGroupWordBook() throws Exception {
        //given
        GroupWordBookResponseDTO.Detail groupWordBookDTO = GroupWordBookResponseDTO.Detail.builder()
                .id(0L)
                .isUsing(true)
                .name("Cy의 단어장-후")
                .groupId(0L)
                .build();

        given(updateGroupWordBookService.update_name(anyLong(),any(GroupWordBookRequestDTO.Update.class))).willReturn(groupWordBookDTO);

        //when
        ResultActions resultActions = requestUpdateGroupWordBook(0L);

        //then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("isUsing").exists())
                .andExpect(jsonPath("name").value(groupWordBookDTO.getName()))
                .andExpect(jsonPath("groupId").exists());
    }
}