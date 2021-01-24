package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CreateDtoToUserWordBookMapper {

    @Mapping(target = "words", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserWordBook createDTOToEntity(CreateWordBookDTO createWordBookDTO);
}
