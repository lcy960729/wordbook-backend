package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateDtoToStudyGroupWordBookMapperTest {

    private final CreateDtoToStudyGroupWordBookMapper createDtoToStudyGroupWordBookMapper
            = Mappers.getMapper(CreateDtoToStudyGroupWordBookMapper.class);

    @Test
    @DisplayName("createWordBookDTOToStudyGroupWordBook 맵핑이 정상적으로 동작 하는 테스트")
    void createDTOTOEntity() {
        //given
        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO.builder()
                .name("testWordBookName")
                .build();

        //when
        StudyGroupWordBook studyGroupWordBook = createDtoToStudyGroupWordBookMapper.createDTOTOEntity(createWordBookDTO);

        //then
        assertThat(studyGroupWordBook.getName()).isEqualTo(createWordBookDTO.getName());
    }
}