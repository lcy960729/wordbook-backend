package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudyGroupWordBookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyGroup", ignore = true)
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    StudyGroupWordBook createDTOTOEntity(CreateWordBookDTO createUserWordBookDTO);

    WordBookDetailDTO entityToResponseDetailDTO(Long userId, StudyGroupWordBook studyGroupWordBook) throws Exception;

    @AfterMapping
    default WordBookDetailDTO makeLinks(@MappingTarget WordBookDetailDTO wordBookDetailDTO, Long userId, StudyGroupWordBook studyGroupWordBook) throws Exception {
        wordBookDetailDTO.makeLinks(userId, studyGroupWordBook.getStudyGroup().getId());
        return wordBookDetailDTO;
    }
}
