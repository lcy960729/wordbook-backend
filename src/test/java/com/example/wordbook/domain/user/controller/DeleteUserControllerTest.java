package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class DeleteUserControllerTest extends UserControllerTest {

    private ResultActions requestCreateGroupWordBook(CreateWordBookDTO createGroupWordBookDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/groupWordBooks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createGroupWordBookDTO))
        );
    }
    @Test
    void delete() {
    }
}