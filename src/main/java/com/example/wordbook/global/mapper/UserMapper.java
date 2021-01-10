package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User createUserDTOToEntity(CreateUserDTO createUserDTO);

    UserDetailDTO userToUserResponseDetailDTO(User user);
}
