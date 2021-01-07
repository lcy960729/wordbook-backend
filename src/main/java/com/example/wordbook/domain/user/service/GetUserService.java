package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.mapper.UserMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDetailDTO getDetailDTOById(Long id) throws Exception {
        return userMapper.userToUserResponseDetailDTO(getEntityById(id));
    }

    public User getEntityById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }
}