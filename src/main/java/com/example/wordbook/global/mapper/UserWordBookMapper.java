package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.wordbook.dto.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserWordBookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    UserWordBook createUserWordBookDTOToEntity(CreateWordBookDTO createUserWordBookDTO);

    @Mapping(target = "ownerId", source = "userWordBook.user.id")
    WordBookDetailDTO entityToUserWordBookDetailDTO(UserWordBook userWordBook);
}
