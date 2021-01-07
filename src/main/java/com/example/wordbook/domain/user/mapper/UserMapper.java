package com.example.wordbook.domain.user.mapper;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserDTOToEntity(CreateUserDTO createUserDTO);

    UserDetailDTO userToUserResponseDetailDTO(User user);
}
