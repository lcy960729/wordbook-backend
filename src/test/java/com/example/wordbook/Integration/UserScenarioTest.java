package com.example.wordbook.Integration;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.wordbook.dto.CreateWordBookDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserScenarioTest extends BaseIntegrationTest {

    private  static final Logger logger = LoggerFactory.getLogger(UserScenarioTest.class);

    private ResultActions postRequest(String url, Object content) throws Exception {
        return mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(content))
        );
    }

    private ResultActions getRequest(String url) throws Exception {
        return mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
        );
    }

    @Test
    void createUser_CreateWordBook_CreateWord() throws Exception {
        //createUser
        //given
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .name("testName")
                .email("testId")
                .pw("testPw")
                .build();

        //when
        ResultActions resultActions = postRequest("/api/v1/users", createUserRequestDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated());

        String getUserUrl = resultActions.andReturn().getResponse().getHeader(HttpHeaders.LOCATION);
        logger.info(getUserUrl);

        //CreateWordBook
        //given
        CreateWordBookDTO createUserWordBookDTO = CreateWordBookDTO.builder()
                .ownerId(Long.parseLong(getUserUrl.substring(getUserUrl.length()-1)))
                .name("testWordBook")
                .build();

        //when
        resultActions = postRequest("/api/v1/userWordBooks", createUserWordBookDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated());

        String getUserWordBookUrl = resultActions.andReturn().getResponse().getHeader(HttpHeaders.LOCATION);
        logger.info(getUserWordBookUrl);

        //getUser
        //when
        resultActions = getRequest(getUserUrl);

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());

        UserDetailResponseDTO userDetailResponseDTO = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), UserDetailResponseDTO.class);
        logger.info(objectMapper.writeValueAsString(userDetailResponseDTO));
    }
}
