package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.dto.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreateStudyGroupServiceTest {


    @Autowired
    private CreateStudyGroupService createStudyGroupService;

    @Autowired
    private CreateUserService createUserService;

    @Test
    void create() throws Exception {
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .email("testemail")
                .name("name")
                .pw("pw")
                .build();
        Long ownerId = createUserService.create(createUserDTO);

        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name("testGroup")
                .groupOwnerId(ownerId)
                .build();

        StudyGroup studyGroup = createStudyGroupService.create(createStudyGroupDTO);

        System.out.println("123");
    }
}