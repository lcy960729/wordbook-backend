package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.mapper.UserMapper;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {GetUserService.class})
@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {

    @Autowired
    private GetUserService getUserService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    void getDetailDTOById() throws Exception {
        //given
        Long id = 0L;
        User user = User.builder()
                .id(id)
                .name("testName")
                .pw("testPw")
                .email("0L")
                .build();

        UserDetailDTO userDetailDTOTemp = UserDetailDTO.builder()
                .id(id)
                .name("testName")
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(user));
        given(userMapper.userToUserResponseDetailDTO(any(User.class))).willReturn(userDetailDTOTemp);

        //when
        UserDetailDTO userDetailDTO = getUserService.getDetailDTOById(id);

        //then
        assertThat(userDetailDTO).isNotNull();
    }
}