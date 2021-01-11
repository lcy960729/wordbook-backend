package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.wordbook.dto.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupWordBookMapper {
    @Mapping(target = "studyGroup", ignore = true)
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    StudyGroupWordBook createGroupWordBookDTOToEntity(CreateWordBookDTO createUserWordBookDTO);

    @Mapping(target = "ownerId", source = "studyGroupWordBook.studyGroup.id")
    WordBookDetailDTO entityToUserWordBookDetailDTO(StudyGroupWordBook studyGroupWordBook);
}
