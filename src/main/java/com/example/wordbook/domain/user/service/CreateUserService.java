package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.request.CreateUserRequestDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.mapper.UserMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CreateUserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDetailResponseDTO create(@Valid CreateUserRequestDTO createUserRequestDTO) {
        User user = userMapper.createUserDTOToEntity(createUserRequestDTO);
        user = userRepository.save(user);

        return userMapper.entityToUserDetailDTO(user);
    }
}
