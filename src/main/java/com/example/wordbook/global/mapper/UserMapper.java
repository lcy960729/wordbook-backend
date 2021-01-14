package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userWordBookList", ignore = true)
    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "id", ignore = true)
    User createUserDTOToEntity(CreateUserRequestDTO createUserRequestDTO);


    @Mapping(target = "wordBookDTOList",  source = "user.userWordBookList", qualifiedByName = "mapToWordBookDTOList")
    @Mapping(target = "studyGroupList", source = "user", qualifiedByName = "mapToStudyGroupDTOList")
    UserDetailResponseDTO entityToUserDetailDTO(User user) throws Exception;

    @Named("mapToStudyGroupDTOList")
    default List<UserDetailResponseDTO.StudyGroupDTO> mapToStudyGroupDTOList(User user) throws Exception {
        ArrayList<UserDetailResponseDTO.StudyGroupDTO> studyGroupDTOArrayList = new ArrayList<>();

        for (Study study : user.getStudyList()) {
            UserDetailResponseDTO.StudyGroupDTO studyGroupDTO = new UserDetailResponseDTO.StudyGroupDTO(study.getStudyGroup().getId(), study.getStudyGroup().getName());
            studyGroupDTOArrayList.add(studyGroupDTO);
        }

        return studyGroupDTOArrayList;
    }


    @Named("mapToWordBookDTOList")
    default List<UserDetailResponseDTO.WordBookDTO> mapToWordBookDTOList(List<UserWordBook> userWordBooks) throws Exception {
        List<UserDetailResponseDTO.WordBookDTO> wordBookDTOList = new ArrayList<>();

        for (UserWordBook userWordBook : userWordBooks) {
            wordBookDTOList.add(new UserDetailResponseDTO.WordBookDTO(userWordBook.getId(), userWordBook.getName()));
        }

        return wordBookDTOList;
    }

    @AfterMapping
    default UserDetailResponseDTO makeLinks(@MappingTarget UserDetailResponseDTO result) throws Exception {
        result.makeLinks();
        return result;
    }
}
