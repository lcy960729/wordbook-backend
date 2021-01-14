package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserWordBookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    UserWordBook createDTOToEntity(CreateWordBookDTO createUserWordBookDTO);

    WordBookDetailDTO entityToResponseDetailDTO(UserWordBook userWordBook) throws Exception;

    @AfterMapping
    default WordBookDetailDTO makeLinks(@MappingTarget WordBookDetailDTO wordBookDetailDTO, UserWordBook userWordBook) throws Exception {
        wordBookDetailDTO.makeLinks(userWordBook.getUser().getId());
        return wordBookDetailDTO;
    }
}
