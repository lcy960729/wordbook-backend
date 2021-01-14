package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.domain.study.service.JoinStudyService;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.repository.UserRepository;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.global.tool.DomainFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WordBookRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(WordBookRepositoryTest.class);

    @Autowired
    private WordBookRepository wordBookRepository;

    @Autowired
    private DomainFactory domainFactory;

    @Test
    public void createUserWordBook() {
        //given
        UserWordBook userWordBook = domainFactory.createUserWordBook(null);

        //when
        userWordBook = wordBookRepository.save(userWordBook);

        //then
        assertThat(userWordBook.getId()).isNotNull();
    }

    @Test
    public void createStudyGroupWordBook() {
        //given
        StudyGroupWordBook studyGroupWordBook = domainFactory.createStudyGroupWordBook(null);

        //when
        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        //then
        assertThat(studyGroupWordBook.getId()).isNotNull();
    }
}