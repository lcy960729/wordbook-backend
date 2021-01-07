package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.mapper.UserMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {CreateUserService.class})
@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Autowired
    private CreateUserService createUserService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    @DisplayName(" 유저 생성시 정상적인 입력값에 대한 성공 테스트")
    void create() {
        //given
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .userId("testId")
                .name("testName")
                .pw("testPw")
                .build();

        User user = User.builder()
                .id(0L)
                .userId(createUserDTO.getUserId())
                .name(createUserDTO.getName())
                .pw(createUserDTO.getPw())
                .build();

        given(userMapper.createUserDTOToEntity(any(CreateUserDTO.class))).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        Long id = createUserService.create(createUserDTO);

        //then
        assertThat(id).isEqualTo(user.getId());
    }
}