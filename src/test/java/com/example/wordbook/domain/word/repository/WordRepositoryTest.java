package com.example.wordbook.domain.word.repository;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepositoryTest;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WordRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(UserWordBookRepositoryTest.class);

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserWordBookRepository userWordBookRepository;

    @Autowired
    private GroupWordBookRepository groupWordBookRepository;

    @Autowired
    private WordBookRepository wordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserWordBook userWordBook;
    private GroupWordBook groupWordBook;

    @BeforeAll
    public void setUp(){
        userWordBook = UserWordBook.builder()
                .userId(User.builder().build())
                .name("test")
                .isUsing(true)
                .words(new ArrayList<>())
                .build();

        userWordBook = userWordBookRepository.save(userWordBook);

        groupWordBook = GroupWordBook.builder()
                .groupId(0L)
                .name("test")
                .isUsing(true)
                .words(new ArrayList<>())
                .build();

        groupWordBook = groupWordBookRepository.save(groupWordBook);
    }

    @Test
    public void createUserWordBook() throws JsonProcessingException {
        //given
        WordBook wordBook = wordBookRepository.findById(1L).get();

        Word word = Word.builder()
                .isUsing(true)
                .voca("apple")
                .meaning("사과")
                .wordBook(wordBook)
                .build();

        //when
        word = wordRepository.save(word);
        Long id = word.getId();

        wordBook.getWords().add(word);
        wordBookRepository.save(wordBook);

        word = wordRepository.findById(id).get();

        //then
        assertThat(word.getId()).isNotNull();
        assertThat(word.getWordBook()).isEqualTo(wordBook);
    }
}