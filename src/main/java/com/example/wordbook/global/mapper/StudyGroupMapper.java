package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyGroupMapper {

    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "studyGroupWordBookList", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    StudyGroup createDTOToEntity(CreateStudyGroupRequestDTO createStudyGroupRequestDTO);

    @Mapping(target = "userList", source = "studyGroup.studyList", qualifiedByName = "mapToUserDTOList")
    @Mapping(target = "wordBookList", source = "studyGroup.studyGroupWordBookList", qualifiedByName = "mapToWordBookDTOList")
    StudyGroupDetailResponseDTO entityToDetailDTO(Long userId, StudyGroup studyGroup);

    @Named("mapToUserDTOList")
    default List<StudyGroupDetailResponseDTO.UserDTO> mapToUserDTOList(List<Study> studyList) {
        List<StudyGroupDetailResponseDTO.UserDTO> userDTOList = new ArrayList<>();
        for (Study study : studyList) {
            userDTOList.add(new StudyGroupDetailResponseDTO.UserDTO(study.getUser().getId(), study.getUser().getName()));
        }
        return userDTOList;
    }

    @Named("mapToWordBookDTOList")
    default List<StudyGroupDetailResponseDTO.WordBookDTO> mapToWordBookDTOList(List<StudyGroupWordBook> studyGroupWordBookList) {

        List<StudyGroupDetailResponseDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();
        for (StudyGroupWordBook studyGroupWordBook : studyGroupWordBookList) {
            wordBookDTOList.add(new StudyGroupDetailResponseDTO.WordBookDTO(studyGroupWordBook.getId(), studyGroupWordBook.getName()));
        }
        return wordBookDTOList;
    }
}
