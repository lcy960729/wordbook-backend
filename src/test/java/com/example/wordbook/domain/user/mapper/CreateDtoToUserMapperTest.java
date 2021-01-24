package com.example.wordbook.domain.user.mapper;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.annotation.DeclareWarning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CreateDtoToUserMapperTest {

    private final CreateDtoToUserMapper createDtoToUserMapper = Mappers.getMapper(CreateDtoToUserMapper.class);

    @Test
    @DisplayName("createUserDTOToEntity 맵핑이 정삭적으로 동작 하는 테스트")
    void createUserDTOToEntity() {
        //given
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        //when
        User user = createDtoToUserMapper.createUserDTOToEntity(createUserDTO);

        //then
        assertThat(user.getName()).isEqualTo(createUserDTO.getName());
        assertThat(user.getEmail()).isEqualTo(createUserDTO.getEmail());
        assertThat(user.getPw()).isEqualTo(createUserDTO.getPw());
    }
}