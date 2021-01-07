package com.example.wordbook.domain.userwordbook.mapper;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserWordBookMapper {
    @Mapping(source = "createUserWordBookDTO.userId", target= "userId.id")
    UserWordBook createUserWordBookDTOToEntity(UserWordBookRequestDTO.Create createUserWordBookDTO);

    UserWordBookResponseDTO.Detail userWordBookToUserWordBookDetailDTO(UserWordBook user);
}
