package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.exception.NotFoundUserException;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class GetUserService {
    private final UserRepository userRepository;
    private final UserToUserDetailDtoMapper userToUserDetailDtoMapper;

    public GetUserService(UserRepository userRepository, UserToUserDetailDtoMapper userToUserDetailDtoMapper) {
        this.userRepository = userRepository;
        this.userToUserDetailDtoMapper = userToUserDetailDtoMapper;
    }

    public UserDetailDTO getDetailDTOById(@NotNull Long id) {
        return userToUserDetailDtoMapper.entityToUserDetailDTO(getEntityById(id));
    }

    public User getEntityById(@NotNull Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

    public boolean isExistingByEmail(@NotBlank String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
