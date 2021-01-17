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
public interface CreateDtoToUserMapper {
    @Mapping(target = "userWordBookList", ignore = true)
    @Mapping(target = "studyList", ignore = true)
    @Mapping(target = "id", ignore = true)
    User createUserDTOToEntity(CreateUserDTO createUserDTO);
}
