package com.example.wordbook.domain.study.service;

import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {
    protected final DomainFactory domainFactory = new DomainFactory();
}
