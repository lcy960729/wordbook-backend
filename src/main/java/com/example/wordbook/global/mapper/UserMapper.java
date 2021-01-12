package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User createUserDTOToEntity(CreateUserRequestDTO createUserRequestDTO);

    UserDetailResponseDTO userToUserDetailDTO(User user);

    default List<UserDetailResponseDTO.StudyGroupDTO> mapToStudyGroupDTOList(User user) {
        if (user.getStudyList() == null)
            return null;

        ArrayList<UserDetailResponseDTO.StudyGroupDTO> studyGroupDTOArrayList = new ArrayList<>();
        for (Study study : user.getStudyList()) {
            UserDetailResponseDTO.StudyGroupDTO studyGroupDTO = new UserDetailResponseDTO.StudyGroupDTO(study.getStudyGroup().getId(), study.getStudyGroup().getName());
            studyGroupDTOArrayList.add(studyGroupDTO);
        }
        return studyGroupDTOArrayList;
    }

    default UserDetailResponseDTO.UserWordBookDTO userWordBookToUserWordBookDTO(UserWordBook userWordBook) {
        return UserDetailResponseDTO.UserWordBookDTO.builder()
                .id(userWordBook.getId())
                .name(userWordBook.getName())
                .build();
    }

    default UserDetailResponseDTO.StudyGroupDTO StudyToUserWordBookDTO(Study study) {
        return UserDetailResponseDTO.StudyGroupDTO.builder()
                .id(study.getStudyGroup().getId())
                .name(study.getStudyGroup().getName())
                .build();
    }
}
