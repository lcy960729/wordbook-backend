package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateDtoToUserWordBookMapperTest {

    private final CreateDtoToUserWordBookMapper createDtoToUserWordBookMapper
            = Mappers.getMapper(CreateDtoToUserWordBookMapper.class);

    @Test
    @DisplayName("createWordBookDTOToUserWordBook 맵핑이 정상적으로 동작 하는 테스트")
    void createDTOToEntity() {
        //given
        CreateWordBookDTO wordBookDTO = CreateWordBookDTO.builder()
                .name("testWordBookName")
                .build();

        //when
        UserWordBook userWordBook = createDtoToUserWordBookMapper.createDTOToEntity(wordBookDTO);

        //then
        assertThat(userWordBook.getName()).isEqualTo(wordBookDTO.getName());
    }
}