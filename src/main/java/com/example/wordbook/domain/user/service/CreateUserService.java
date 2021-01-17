package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import com.example.wordbook.domain.user.mapper.CreateDtoToUserMapper;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public class CreateUserService {

    private final UserRepository userRepository;

    private final UserToUserDetailDtoMapper userToUserDetailDtoMapper;
    private final CreateDtoToUserMapper createDtoToUserMapper;

    private final GetUserService getUserService;

    public CreateUserService(UserToUserDetailDtoMapper userToUserDetailDtoMapper, CreateDtoToUserMapper createDtoToUserMapper, UserRepository userRepository, GetUserService getUserService) {
        this.userToUserDetailDtoMapper = userToUserDetailDtoMapper;
        this.createDtoToUserMapper = createDtoToUserMapper;
        this.userRepository = userRepository;
        this.getUserService = getUserService;
    }

    private boolean isExistingByEmail(String email) {
        return getUserService.isExistingByEmail(email);
    }

    public UserDetailDTO create(@Valid CreateUserDTO createUserDTO) {
        if (isExistingByEmail(createUserDTO.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_DUPLICATION);
        }

        User user = createDtoToUserMapper.createUserDTOToEntity(createUserDTO);
        user = userRepository.save(user);

        return userToUserDetailDtoMapper.entityToUserDetailDTO(user);
    }
}
