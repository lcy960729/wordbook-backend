package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserCreateControllerTest extends UserControllerTest {

    private ResultActions requestCreateUser(CreateUserRequestDTO createUserRequestDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDTO))
        );
    }

    @Test
    @DisplayName("스터디 그룹과 단어장이 없을 때 Body와 Created를 반환")
    void createUser_NotEmptyStudyGroups() throws Exception {
        //given
        CreateUserRequestDTO userCreateDTOConsistingWellFormedInput = CreateUserRequestDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(0L)
                .name("testName")
                .email("testEmail")
                .build();
        given(createUserService.create(any(CreateUserRequestDTO.class))).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("wordBookDTOList").isEmpty())
                .andExpect(jsonPath("studyGroupList").isEmpty())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_user").exists())
                .andExpect(jsonPath("_links.delete_user").exists());
    }

    @Test
    @DisplayName("스터디 그룹과 단어장이 있을 때 각자의 상태로 가는 링크가 포함된 Body와 Created를 반환")
    void create() throws Exception {
        //given
        CreateUserRequestDTO userCreateDTOConsistingWellFormedInput = CreateUserRequestDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        List<UserDetailResponseDTO.StudyGroupDTO> studyGroupDTOList = new ArrayList<>();

        long userId = 0L;
        for (int i = 0; i < 5; ++i) {
            studyGroupDTOList.add(new UserDetailResponseDTO.StudyGroupDTO(userId, (long)i, "testGroup" + i));
        }

        List<UserDetailResponseDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            wordBookDTOList.add(new UserDetailResponseDTO.WordBookDTO(userId, (long)i, "testWordBook" + i));
        }

        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(userId)
                .name("testName")
                .email("testEmail")
                .studyGroupList(studyGroupDTOList)
                .wordBookDTOList(wordBookDTOList)
                .build();

        given(createUserService.create(any(CreateUserRequestDTO.class))).willReturn(userDetailResponseDTO);

        //when
        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);

        //then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("wordBookDTOList").isArray())
                .andExpect(jsonPath("wordBookDTOList[*]._links.get_wordBook").exists())
                .andExpect(jsonPath("studyGroupList").isArray())
                .andExpect(jsonPath("studyGroupList[*]._links.get_studyGroup").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update_user").exists())
                .andExpect(jsonPath("_links.delete_user").exists());
    }

//    @Test
//    @DisplayName("유저가 추가가 잘못된 입력값에 의해 실패되었을때 404를 반")
//    void create_ErrorTest() throws Exception {
//        //given
//        CreateUserDTO userCreateDTOConsistingWellFormedInput = CreateUserDTO.builder()
//                .email(null)
//                .name(null)
//                .pw(null)
//                .build();
//
//        given(createUserService.create(any(CreateUserDTO.class))).willReturn(0L);
//
//        //when
//        ResultActions resultActions = requestCreateUser(userCreateDTOConsistingWellFormedInput);
//
//        //then
//        resultActions.andDo(print())
//                .andExpect(status().isBadRequest());
//    }
}