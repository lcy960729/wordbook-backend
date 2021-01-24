package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateDtoToStudyGroupWordBookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyGroup", ignore = true)
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    StudyGroupWordBook createDTOTOEntity(CreateWordBookDTO createWordBookDTO);
}
