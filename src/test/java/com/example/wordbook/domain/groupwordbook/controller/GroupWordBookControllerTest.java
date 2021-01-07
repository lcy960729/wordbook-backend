package com.example.wordbook.domain.groupwordbook.controller;

import com.example.wordbook.domain.groupwordbook.service.CreateGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.DeleteGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.GetGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.UpdateGroupWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.mock.mockito.MockBean;

public class GroupWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateGroupWordBookService createGroupWordBookService;
    @MockBean
    protected GetGroupWordBookService getGroupWordBookService;
    @MockBean
    protected UpdateGroupWordBookService updateGroupWordBookService;
    @MockBean
    protected DeleteGroupWordBookService deleteGroupWordBookService;
}
