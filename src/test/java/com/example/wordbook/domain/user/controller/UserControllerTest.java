package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.user.service.UpdateUserService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserControllerTest extends BaseControllerTest {
    @MockBean
    protected CreateUserService createUserService;

    @MockBean
    protected GetUserService getUserService;

    @MockBean
    protected UpdateUserService updateUserService;
}
