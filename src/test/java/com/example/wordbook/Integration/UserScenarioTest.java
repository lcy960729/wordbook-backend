package com.example.wordbook.Integration;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
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
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("testName")
                .userId("testId")
                .pw("testPw")
                .build();

        //when
        ResultActions resultActions = postRequest("/api/v1/users", createUserDTO);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated());

        String getUserUrl = resultActions.andReturn().getResponse().getHeader(HttpHeaders.LOCATION);
        logger.info(getUserUrl);

        //CreateWordBook
        //given
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create.builder()
                .userId(Long.parseLong(getUserUrl.substring(getUserUrl.length()-1)))
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

        UserDetailDTO userDetailDTO = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), UserDetailDTO.class);
        logger.info(objectMapper.writeValueAsString(userDetailDTO));
    }
}
