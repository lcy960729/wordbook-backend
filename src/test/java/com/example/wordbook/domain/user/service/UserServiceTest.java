package com.example.wordbook.domain.user.service;

import com.example.wordbook.domain.user.mapper.CreateDtoToUserMapper;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Ignore
public class UserServiceTest {
    protected final UserToUserDetailDtoMapper userToUserDetailDtoMapper = Mappers.getMapper(UserToUserDetailDtoMapper.class);
    protected final CreateDtoToUserMapper createDtoToUserMapper = Mappers.getMapper(CreateDtoToUserMapper.class);
}
