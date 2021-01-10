package com.example.wordbook.domain.wordbook.controller;


import com.example.wordbook.domain.wordbook.service.WordBookServiceFactory;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import com.example.wordbook.global.controller.BaseControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(controllers = WordBookController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class WordBookControllerTest extends BaseControllerTest {
    @MockBean
    protected WordBookServiceFactory wordBookServiceFactory;
}