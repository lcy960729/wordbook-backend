package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
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

    public Long create(@Valid CreateUserDTO createUserDTO) {
        User user = userMapper.createUserDTOToEntity(createUserDTO);
        user = userRepository.save(user);

        return user.getId();
    }
}
