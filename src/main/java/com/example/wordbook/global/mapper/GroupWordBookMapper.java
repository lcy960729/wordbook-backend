package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.global.config.mapper.IgnoreUnmappedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface GroupWordBookMapper {
    @Mapping(target = "words", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "group", ignore = true)
    GroupWordBook createGroupWordBookDTOToEntity(WordBookRequestDTO.Create createUserWordBookDTO);

//    @Mapping(target = "ownerId", source = "userWordBook.user.id")
    WordBookResponseDTO.Detail entityToUserWordBookDetailDTO(GroupWordBook groupWordBook);
}
