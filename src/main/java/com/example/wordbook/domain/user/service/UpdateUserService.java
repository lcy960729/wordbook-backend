package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.request.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateUserService {
    private final UserRepository userRepository;
    private final UserToUserDetailDtoMapper userToUserDetailDtoMapper;

    private final GetUserService getUserService;

    public UpdateUserService(UserRepository userRepository, UserToUserDetailDtoMapper userToUserDetailDtoMapper, GetUserService getUserService) {
        this.userRepository = userRepository;
        this.userToUserDetailDtoMapper = userToUserDetailDtoMapper;
        this.getUserService = getUserService;
    }

    public UserDetailDTO update(@NotNull Long id, @Valid UpdateUserDTO updateUserDTO) {
        User user = getUserService.getEntityById(id);
        user.setName(updateUserDTO.getName());

        user = userRepository.save(user);

        return userToUserDetailDtoMapper.entityToUserDetailDTO(user);
    }
}
