package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyGroupMapper {

    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "studyGroupWordBookList", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    StudyGroup createDTOToEntity(CreateStudyGroupRequestDTO createStudyGroupRequestDTO);

    @Mapping(target = "id", source = "studyGroup.id")
    @Mapping(target = "userDTOList", source = "studyGroup.studyList", qualifiedByName = "mapToUserDTOList")
    @Mapping(target = "wordBookDTOList", source = "studyGroup.studyGroupWordBookList", qualifiedByName = "mapToWordBookDTOList")
    StudyGroupDetailResponseDTO entityToResponseDTO(Study study, StudyGroup studyGroup) throws Exception;

    @Named("mapToUserDTOList")
    default List<StudyGroupDetailResponseDTO.UserDTO> mapToUserDTOList(List<Study> studyList) {
        List<StudyGroupDetailResponseDTO.UserDTO> userDTOList = new ArrayList<>();
        for (Study study : studyList) {
            User user = study.getUser();
            userDTOList.add(new StudyGroupDetailResponseDTO.UserDTO(user.getId(), user.getName()));
        }
        return userDTOList;
    }

    @Named("mapToWordBookDTOList")
    default List<StudyGroupDetailResponseDTO.WordBookDTO> mapToWordBookDTOList(List<StudyGroupWordBook> studyGroupWordBookList)  {
        List<StudyGroupDetailResponseDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();
        for (StudyGroupWordBook studyGroupWordBook : studyGroupWordBookList) {
            wordBookDTOList.add(new StudyGroupDetailResponseDTO.WordBookDTO(studyGroupWordBook.getId(), studyGroupWordBook.getName()));
        }
        return wordBookDTOList;
    }

    @AfterMapping
    default StudyGroupDetailResponseDTO StudyGroupCreateResponseDTO(@MappingTarget StudyGroupDetailResponseDTO result, Study study) throws Exception {
        result.makeLinks(study);
        return result;
    }

}
