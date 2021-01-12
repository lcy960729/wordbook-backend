package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.studyGroup.dto.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudyGroupMapper {


    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "studyGroupWordBookList", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    StudyGroup createDTOToEntity(CreateStudyGroupDTO createStudyGroupDTO);

    @Mapping(target = "wordBookList", ignore = true)
    @Mapping(target = "userList", ignore = true)
    StudyGroupDetailDTO entityToDetailDTO(StudyGroup studyGroup);
}
