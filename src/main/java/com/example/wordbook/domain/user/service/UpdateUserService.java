package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.mapper.UserMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GetUserService getUserService;

    public UpdateUserService(UserRepository userRepository, UserMapper userMapper, GetUserService getUserService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.getUserService = getUserService;
    }

    public UserDetailDTO update(Long id, UpdateUserDTO updateUserDTO) throws Exception {
        User user = getUserService.getEntityById(id);
        user.setName(updateUserDTO.getName());
        user = userRepository.save(user);

        return userMapper.userToUserResponseDetailDTO(user);
    }
}
