package com.example.wordbook.domain.word.repository;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
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
    private static final Logger logger = LoggerFactory.getLogger(WordRepositoryTest.class);

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordBookRepository wordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserWordBook userWordBook;
    private GroupWordBook groupWordBook;

    @BeforeAll
    public void setUp(){
        userWordBook = UserWordBook.builder()
                .user(User.builder().build())
                .name("test")
                .isUsing(true)
                .words(new ArrayList<>())
                .build();

        userWordBook = wordBookRepository.save(userWordBook);

        groupWordBook = GroupWordBook.builder()
                .group(0L)
                .name("test")
                .isUsing(true)
                .words(new ArrayList<>())
                .build();

        groupWordBook = wordBookRepository.save(groupWordBook);
    }

    @Test
    public void createUserWordBook() throws JsonProcessingException {
        //given
        UserWordBook wordBook = (UserWordBook) wordBookRepository.findById(1L).get();

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