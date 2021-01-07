package com.example.wordbook.domain.userwordbook.repository;

import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.exception.WordBookNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class UserWordBookRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(UserWordBookRepositoryTest.class);

    @Autowired
    private UserWordBookRepository userWordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createUserWordBook(){
        //given
        UserWordBook userWordBook = UserWordBook.builder()
                .isUsing(true)
                .name("lcy단어장")
                .words(new ArrayList<>())
                .build();

        //when
        userWordBook = userWordBookRepository.save(userWordBook);

        //then
        assertThat(userWordBook.getId()).isNotNull();
    }

    @Test
    public void getUserWordBook() throws JsonProcessingException {
        //given
        UserWordBook userWordBookTemp = UserWordBook.builder()
                .isUsing(true)
                .name("lcy단어장")
                .words(new ArrayList<>())
                .build();
        userWordBookTemp = userWordBookRepository.save(userWordBookTemp);

        Long id = userWordBookTemp.getId();

        //when
        UserWordBook userWordBook = userWordBookRepository.findById(id)
                .orElseThrow(()-> new WordBookNotFoundException(id.toString()));

        logger.info(objectMapper.writeValueAsString(userWordBook));
        //then
        assertThat(userWordBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(userWordBookTemp);
    }
}