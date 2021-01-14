package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.user.service.UpdateUserService;
import com.example.wordbook.global.component.LinkFactory;
import com.example.wordbook.global.component.LinkName;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.stream.LongStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateUserService createUserService;

    @MockBean
    protected GetUserService getUserService;

    @MockBean
    protected UpdateUserService updateUserService;

    public void urlExistCheck(ResultActions resultActions, LinkName linkName) throws Exception {
        resultActions
                .andExpect(jsonPath("_links." + linkName).exists());
//              .andExpect(jsonPath(url + ".href").value(link.toUri().toString()));
    }

    protected UserDetailResponseDTO getUserDetailResponseDTO() throws Exception {
        long userId = 0L;
        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
                .id(userId)
                .name("testName")
                .email("testEmail")
                .studyGroupList(new ArrayList<>())
                .wordBookDTOList(new ArrayList<>())
                .build();

        long studyGroupSize = 3;
        LongStream.range(0, studyGroupSize).forEach((id) -> {
            try {
                UserDetailResponseDTO.StudyGroupDTO studyGroupDTO = new UserDetailResponseDTO.StudyGroupDTO(id, "testStudyGroup" + id);
                userDetailResponseDTO.getStudyGroupList().add(studyGroupDTO);

                UserDetailResponseDTO.WordBookDTO wordBookDTO = new UserDetailResponseDTO.WordBookDTO(id, "testWordBook" + id);
                userDetailResponseDTO.getWordBookDTOList().add(wordBookDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        userDetailResponseDTO.makeLinks();

        return userDetailResponseDTO;
    }
}
