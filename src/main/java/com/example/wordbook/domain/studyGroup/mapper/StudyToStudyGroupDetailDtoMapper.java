package com.example.wordbook.domain.studyGroup.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.global.enums.StudyGroupRole;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyToStudyGroupDetailDtoMapper {
    @Mapping(target = "id", source = "study.studyGroup.id")
    @Mapping(target = "name", source = "study.studyGroup.name")
    @Mapping(target = "userDTOList", source = "study.studyGroup.studyList", qualifiedByName = "mapToUserDTOList")
    @Mapping(target = "wordBookDTOList", source = "study.studyGroup.studyGroupWordBookList", qualifiedByName = "mapToWordBookDTOList")
    StudyGroupDetailDTO entityToStudGroupResponseDTO(Study study);

    @Named("mapToUserDTOList")
    default List<StudyGroupDetailDTO.UserDTO> mapToUserDTOList(List<Study> studyList) {
        List<StudyGroupDetailDTO.UserDTO> userDTOList = new ArrayList<>();
        for (Study study : studyList) {
            User user = study.getUser();
            userDTOList.add(new StudyGroupDetailDTO.UserDTO(user.getId(), user.getName()));
        }
        return userDTOList;
    }

    @Named("mapToWordBookDTOList")
    default List<StudyGroupDetailDTO.WordBookDTO> mapToWordBookDTOList(List<StudyGroupWordBook> studyGroupWordBookList)  {
        List<StudyGroupDetailDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();
        for (StudyGroupWordBook studyGroupWordBook : studyGroupWordBookList) {
            wordBookDTOList.add(new StudyGroupDetailDTO.WordBookDTO(studyGroupWordBook.getId(), studyGroupWordBook.getName()));
        }
        return wordBookDTOList;
    }

    @AfterMapping
    default StudyGroupDetailDTO StudyGroupResponseDTO(@MappingTarget StudyGroupDetailDTO studyGroupDetailDTO, Study study) {
        Long userId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        StudyGroupRole studyGroupRole = study.getStudyGroupRole();

        studyGroupDetailDTO.makeLinks(userId, studyGroupId, studyGroupRole);

        return studyGroupDetailDTO;
    }
}
