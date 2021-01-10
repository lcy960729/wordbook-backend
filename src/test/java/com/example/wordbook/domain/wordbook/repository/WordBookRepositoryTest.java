package com.example.wordbook.domain.wordbook.repository;

import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WordBookRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(WordBookRepositoryTest.class);

    @Autowired
    private WordBookRepository wordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createUserWordBook() throws JsonProcessingException {
        //given
        UserWordBook userWordBook = UserWordBook.builder()
                .isUsing(true)
                .name("lcy단어장")
                .words(new ArrayList<>())
                .build();

        //when
        userWordBook = wordBookRepository.save(userWordBook);

        logger.info(objectMapper.writeValueAsString(userWordBook));
        logger.info(objectMapper.writeValueAsString((UserWordBook) wordBookRepository.findById(userWordBook.getId()).orElseThrow(RuntimeException::new)));
        logger.info(objectMapper.writeValueAsString((GroupWordBook) wordBookRepository.findById(userWordBook.getId()).orElseThrow(RuntimeException::new)));
        //then
        assertThat(userWordBook.getId()).isNotNull();
    }



//    @Test
//    public void getUserWordBook() throws JsonProcessingException {
//        //given
//        UserWordBook userWordBookTemp = UserWordBook.builder()
//                .isUsing(true)
//                .name("lcy단어장")
//                .words(new ArrayList<>())
//                .build();
//        userWordBookTemp = userWordBookRepository.save(userWordBookTemp);
//
//        Long id = userWordBookTemp.getId();
//
//        //when
//        UserWordBook userWordBook = userWordBookRepository.findById(id)
//                .orElseThrow(()-> new WordBookNotFoundException(id.toString()));
//
//        logger.info(objectMapper.writeValueAsString(userWordBook));
//        //then
//        assertThat(userWordBook)
//                .isNotNull()
//                .usingRecursiveComparison()
//                .isEqualTo(userWordBookTemp);
//    }
}