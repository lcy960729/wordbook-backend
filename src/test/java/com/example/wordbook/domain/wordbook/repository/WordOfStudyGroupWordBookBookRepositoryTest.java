package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WordOfStudyGroupWordBookBookRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(WordOfStudyGroupWordBookBookRepositoryTest.class);

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