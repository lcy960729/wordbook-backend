package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.studyGroup.dto.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;

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
    private CreateStudyGroupService createStudyGroupService;
    @Autowired
    private GetStudyGroupService getStudyGroupService;

    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private GetUserService getUserService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createUserWordBook() {
        //given
        UserWordBook userWordBook = UserWordBook.builder()
                .isUsing(true)
                .name("testWordBook")
                .words(new ArrayList<>())
                .build();

        //when
        userWordBook = wordBookRepository.save(userWordBook);

        //then
        assertThat(userWordBook.getId()).isNotNull();
    }

    @Test
    public void createStudyGroupWordBook() {
        //given
        StudyGroupWordBook studyGroupWordBook = StudyGroupWordBook.builder()
                .isUsing(true)
                .name("testWordBook")
                .build();

        //when
        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        //then
        assertThat(studyGroupWordBook.getId()).isNotNull();
    }

    @Test
    @Transactional
    public void getUserWordBook() throws Exception {
        //given
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .email("lcy960729")
                .name("testName")
                .pw("testPw")
                .build();
        Long userId = createUserService.create(createUserRequestDTO).getId();

        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name("testGroup")
                .groupOwnerId(userId)
                .build();
        Long studyGroupId = createStudyGroupService.create(createStudyGroupDTO);

        StudyGroup studyGroup = getStudyGroupService.getEntityById(studyGroupId);

        StudyGroupWordBook tempStudyGroupWordBook = StudyGroupWordBook.builder()
                .isUsing(true)
                .name("testWordBook")
                .studyGroup(studyGroup)
                .build();
        tempStudyGroupWordBook = wordBookRepository.save(tempStudyGroupWordBook);

        //when
        StudyGroupWordBook studyGroupWordBook = (StudyGroupWordBook) wordBookRepository.findById(tempStudyGroupWordBook.getId()).orElseThrow(NotFoundWordBookException::new);

        //then
        assertThat(studyGroupWordBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(tempStudyGroupWordBook);
    }
}