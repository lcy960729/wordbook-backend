package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.request.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.exception.NotFoundUserException;
import com.example.wordbook.domain.user.repository.UserRepository;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

class UpdateUserServiceTest extends UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GetUserService getUserService;

    private UpdateUserService updateUserService;

    @BeforeEach
    void setUp() {
        updateUserService = new UpdateUserService(userRepository, userToUserDetailDtoMapper, getUserService);
    }

    @Test
    @DisplayName("정상적으로 유저를 수정하는 테스트")
    void update() {
        //given
        UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .name("updateName")
                .build();

        long userId = 0L;

        User user = domainFactory.getUserOfStudyGroupNormal();

        given(getUserService.getEntityById(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        UserDetailDTO userDetailDTO = updateUserService.update(userId, updateUserDTO);

        //then
        assertThat(userDetailDTO).isNotNull();
        assertThat(userDetailDTO.getName()).isEqualTo(updateUserDTO.getName());
    }

    @Test
    @DisplayName("존재하지 않는 유저를 수정시 에러를 반환하는 테스트")
    void update_InvalidUser_ErrorTest() {
        //given
        UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .name("updateName")
                .build();

        long userId = 0L;

        given(getUserService.getEntityById(anyLong())).willThrow(NotFoundUserException.class);

        //when
        Throwable throwable = catchThrowable(()->updateUserService.update(userId, updateUserDTO));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(NotFoundUserException.class);
    }
}