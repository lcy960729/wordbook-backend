package com.example.wordbook.domain.userwordbook.controller;

import com.example.wordbook.domain.userwordbook.service.CreateUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.DeleteUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.GetUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.UpdateUserWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserWordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected GetUserWordBookService getUserWordBookService;
    @MockBean
    protected CreateUserWordBookService createUserWordBookService;
    @MockBean
    protected UpdateUserWordBookService updateUserWordBookService;
    @MockBean
    protected DeleteUserWordBookService deleteUserWordBookService;
}