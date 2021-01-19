package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.user.service.UpdateUserService;
import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

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



    protected UserDetailDTO getUserDetailResponseDTO(String name, String email) {
        UserDetailDTO userDetailDTO = UserDetailDTO.builder()
                .id(0L)
                .name(name)
                .email(email)
                .studyGroupDTOList(new ArrayList<>())
                .wordBookDTOList(new ArrayList<>())
                .build();

        long studyGroupSize = 3;
        LongStream.range(0, studyGroupSize).forEach((id) -> {
            try {
                UserDetailDTO.StudyGroupDTO studyGroupDTO = new UserDetailDTO.StudyGroupDTO(id, "testStudyGroup" + id);
                userDetailDTO.getStudyGroupDTOList().add(studyGroupDTO);

                UserDetailDTO.WordBookDTO wordBookDTO = new UserDetailDTO.WordBookDTO(id, "testWordBook" + id);
                userDetailDTO.getWordBookDTOList().add(wordBookDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        userDetailDTO.makeLinks();

        return userDetailDTO;
    }
}
