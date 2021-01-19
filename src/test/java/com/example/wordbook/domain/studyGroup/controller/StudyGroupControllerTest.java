package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.controller.BaseControllerTest;
import com.example.wordbook.global.enums.StudyGroupRole;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.stream.LongStream;

@WebMvcTest(controllers = StudyGroupController.class)
public class StudyGroupControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateStudyGroupService createStudyGroupService;

    @MockBean
    protected GetStudyGroupService getStudyGroupService;

    protected StudyGroupDetailDTO getStudyGroupDetailDTO(String name, StudyGroupRole studyGroupRole) {
        StudyGroupDetailDTO studyGroupDetailDTO = StudyGroupDetailDTO.builder()
                .id(0L)
                .name(name)
                .userDTOList(new ArrayList<>())
                .wordBookDTOList(new ArrayList<>())
                .build();

        long userAndWordSize = 3;
        LongStream.range(0, userAndWordSize).forEach((id) -> {
            try {
                StudyGroupDetailDTO.UserDTO userDTO = new StudyGroupDetailDTO.UserDTO(id, "testStudyGroup" + id);
                studyGroupDetailDTO.getUserDTOList().add(userDTO);

                StudyGroupDetailDTO.WordBookDTO wordBookDTO = new StudyGroupDetailDTO.WordBookDTO(id, "testWordBook" + id);
                studyGroupDetailDTO.getWordBookDTOList().add(wordBookDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        studyGroupDetailDTO.makeLinks(1L, 0L, studyGroupRole);

        return studyGroupDetailDTO;
    }
}
