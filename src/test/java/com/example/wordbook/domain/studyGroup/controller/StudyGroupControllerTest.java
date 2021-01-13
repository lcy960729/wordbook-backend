package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(controllers = StudyGroupController.class)
public class StudyGroupControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateStudyGroupService createStudyGroupService;

    @MockBean
    protected GetStudyGroupService getStudyGroupService;
}
