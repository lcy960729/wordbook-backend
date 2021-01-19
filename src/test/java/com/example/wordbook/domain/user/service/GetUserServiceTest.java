package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.exception.NotFoundUserException;
import com.example.wordbook.domain.user.repository.UserRepository;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

class GetUserServiceTest extends UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("아이디로 UserDetailDTO를 반환하는 테스트")
    void getDetailDTOById() {
        //given
        GetUserService getUserService = new GetUserService(userRepository, userToUserDetailDtoMapper);

        long userId = 0L;
        given(userRepository.findById(anyLong())).willReturn(Optional.of(DomainFactory.createUser(userId)));

        //when
        UserDetailDTO userDetailDTO = getUserService.getDetailDTOById(userId);

        //then
        assertThat(userDetailDTO).isNotNull();
    }

    @Test
    @DisplayName("유효한 Id일 User를 반환하는 테스트")
    void getEntityById() {
        //given때
        GetUserService getUserService = new GetUserService(userRepository, userToUserDetailDtoMapper);

        given(userRepository.findById(anyLong())).willReturn(Optional.of(DomainFactory.createUser(0L)));

        //when
        User user = getUserService.getEntityById(0L);

        //then
        assertThat(user).isNotNull();
    }


    @Test
    @DisplayName("존재하지 않는 Id로 User 조회시 에러를 반환하는 테스트")
    void getEntityById_InvalidId_Return_Error() {
        //given
        GetUserService getUserService = new GetUserService(userRepository, userToUserDetailDtoMapper);

        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> getUserService.getEntityById(0L));

        //then
        assertThat(throwable).isInstanceOf(NotFoundUserException.class);
    }

    @Test
    @DisplayName("이미 존재하는 이메일이 있을때 true를 반환하는 테스트")
    void isExistingByEmail() {
        //given
        GetUserService getUserService = new GetUserService(userRepository, userToUserDetailDtoMapper);

        User user = DomainFactory.createUser(0L);
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

        //when
        boolean result = getUserService.isExistingByEmail(user.getEmail());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이미 존재하는 이메일이 없을때 false를 반환하는 테스트")
    void isNotExistingByEmail() {
//given
        GetUserService getUserService = new GetUserService(userRepository, userToUserDetailDtoMapper);

        given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());

        //when
        boolean result = getUserService.isExistingByEmail("testEamil@test.com");

        //then
        assertThat(result).isFalse();
    }
}