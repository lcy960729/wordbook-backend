package com.example.wordbook.domain.word.mapper;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.entity.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateDtoToWordMapperTest {

    private final CreateDtoToWordMapper createDtoToWordMapper = Mappers.getMapper(CreateDtoToWordMapper.class);

    @Test
    @DisplayName("createWordDto To Word 맵핑이 정상적으로 동작하는 테스트")
    void createDTOToEntity() {
        //given
        CreateWordDTO createWordDTO = CreateWordDTO.builder()
                .voca("test")
                .meaning("테스트")
                .build();

        //when
        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);

        //then
        assertThat(word).isNotNull();
        assertThat(word.getVoca()).isEqualTo(createWordDTO.getVoca());
        assertThat(word.getMeaning()).isEqualTo(createWordDTO.getMeaning());
    }
}