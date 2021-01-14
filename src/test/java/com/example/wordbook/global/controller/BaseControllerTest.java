package com.example.wordbook.global.controller;

import com.example.wordbook.global.component.LinkName;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@Ignore
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    public void urlExistCheck(ResultActions resultActions, String fieldName, LinkName linkName) throws Exception {
        resultActions
                .andExpect(jsonPath(fieldName + "._links." + linkName).exists());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }


    public void fieldExistCheck(ResultActions resultActions, String fieldName) throws Exception {
        resultActions
                .andExpect(jsonPath(fieldName).exists());
    }
}
