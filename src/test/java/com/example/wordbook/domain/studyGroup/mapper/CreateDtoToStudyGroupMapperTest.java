package com.example.wordbook.domain.studyGroup.mapper;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.global.tool.DomainFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


class CreateDtoToStudyGroupMapperTest {

    private final CreateDtoToStudyGroupMapper createDtoToStudyGroupMapper = Mappers.getMapper(CreateDtoToStudyGroupMapper.class);

    @Test
    @DisplayName("createDTOToEntity 맵핑이 정상적으로 동작 하는 테스트")
    void createDTOToEntity() {
        //given
        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name("testName")
                .build();

        StudyGroup studyGroup = createDtoToStudyGroupMapper.createDTOToEntity(createStudyGroupDTO);

        assertThat(studyGroup).isNotNull();
        assertThat(studyGroup.getName())
                .isEqualTo(createStudyGroupDTO.getName());
    }
}