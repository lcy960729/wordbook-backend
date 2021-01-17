package com.example.wordbook.domain.studyGroup.mapper;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CreateDtoToStudyGroupMapper {

    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "studyGroupWordBookList", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    StudyGroup createDTOToEntity(CreateStudyGroupDTO createStudyGroupDTO);
}
