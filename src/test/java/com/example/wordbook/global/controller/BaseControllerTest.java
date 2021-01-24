package com.example.wordbook.global.controller;

import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.tool.DomainFactory;
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

    protected final static DomainFactory domainFactory = new DomainFactory();

    public void urlExistCheck(ResultActions resultActions, DomainLink domainLink) throws Exception {
        resultActions
                .andExpect(jsonPath("_links." + domainLink).exists());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }


    public void urlNotExistCheck(ResultActions resultActions, DomainLink domainLink) throws Exception {
        resultActions
                .andExpect(jsonPath("_links." + domainLink).doesNotExist());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }

    protected void urlNotExistCheck(ResultActions resultActions, String fieldName, DomainLink domainLink) throws Exception {
        resultActions
                .andExpect(jsonPath(fieldName + "._links." + domainLink).doesNotExist());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }

    protected void urlExistCheck(ResultActions resultActions, String fieldName, DomainLink domainLink) throws Exception {
        resultActions
                .andExpect(jsonPath(fieldName + "._links." + domainLink).exists());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }

    protected void fieldExistCheck(ResultActions resultActions, String fieldName) throws Exception {
        resultActions
                .andExpect(jsonPath(fieldName).exists());
    }
}
