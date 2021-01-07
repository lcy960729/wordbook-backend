package com.example.wordbook.domain.groupwordbook.repository;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.exception.WordBookNotFoundException;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupWordBookRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(GroupWordBookRepositoryTest.class);

    @Autowired
    private GroupWordBookRepository groupWordBookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createGroupWordBook(){
        //given
        GroupWordBook groupWordBook = GroupWordBook.builder()
                .isUsing(true)
                .name("lcy단어장")
                .words(new ArrayList<>())
                .build();

        //when
        groupWordBook = groupWordBookRepository.save(groupWordBook);

        //then
        assertThat(groupWordBook.getId()).isNotNull();
    }

    @Test
    public void getGroupWordBook() throws JsonProcessingException {
        //given
        GroupWordBook groupWordBookTemp = GroupWordBook.builder()
                .isUsing(true)
                .name("lcy단어장")
                .words(new ArrayList<>())
                .build();
        groupWordBookTemp = groupWordBookRepository.save(groupWordBookTemp);

        Long id = groupWordBookTemp.getId();

        //when
        GroupWordBook groupWordBook = groupWordBookRepository.findById(id)
                .orElseThrow(()-> new WordBookNotFoundException(id.toString()));

        logger.info(objectMapper.writeValueAsString(groupWordBook));
        //then
        assertThat(groupWordBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(groupWordBookTemp);
    }
}