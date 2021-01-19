package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.mapper.CreateDtoToUserMapper;
import com.example.wordbook.domain.user.mapper.CreateDtoToUserMapperImpl;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapperImpl;
import com.example.wordbook.domain.user.repository.UserRepository;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CreateUserServiceTest extends UserServiceTest{
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GetUserService getUserService;

    @Test
    @DisplayName("정상적으로 유저 생성하는 테스트")
    void create() {
        //given
        CreateUserService createUserService = new CreateUserService(userToUserDetailDtoMapper, createDtoToUserMapper, userRepository, getUserService);

        User user = DomainFactory.createUser(0L);

        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .pw(user.getPw())
                .build();

        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        UserDetailDTO userDetailDTO = createUserService.create(createUserDTO);

        //then
        assertThat(userDetailDTO.getId()).isEqualTo(user.getId());
    }
}