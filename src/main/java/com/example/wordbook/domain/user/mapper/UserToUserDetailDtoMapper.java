package com.example.wordbook.domain.user.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserToUserDetailDtoMapper {
    @Mapping(target = "wordBookDTOList",  source = "user.userWordBookList", qualifiedByName = "mapToWordBookDTOList")
    @Mapping(target = "studyGroupDTOList", source = "user.studyList", qualifiedByName = "mapToStudyGroupDTOList")
    UserDetailDTO entityToUserDetailDTO(User user) ;

    @Named("mapToStudyGroupDTOList")
    default List<UserDetailDTO.StudyGroupDTO> mapToStudyGroupDTOList(List<Study> studyList) {
        ArrayList<UserDetailDTO.StudyGroupDTO> studyGroupDTOArrayList = new ArrayList<>();

        for (Study study : studyList) {
            studyGroupDTOArrayList.add(new UserDetailDTO.StudyGroupDTO(study.getStudyGroup().getId(), study.getStudyGroup().getName()));
        }

        return studyGroupDTOArrayList;
    }


    @Named("mapToWordBookDTOList")
    default List<UserDetailDTO.WordBookDTO> mapToWordBookDTOList(List<UserWordBook> userWordBooks) {
        List<UserDetailDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();

        for (UserWordBook userWordBook : userWordBooks) {
            wordBookDTOList.add(new UserDetailDTO.WordBookDTO(userWordBook.getId(), userWordBook.getName()));
        }

        return wordBookDTOList;
    }

    @AfterMapping
    default UserDetailDTO makeLinks(@MappingTarget UserDetailDTO userDetailDTO) {
        userDetailDTO.makeLinks();

        return userDetailDTO;
    }
}
