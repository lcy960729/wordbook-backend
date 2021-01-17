package com.example.wordbook.domain.word.mapper;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreateDtoToWordMapper {
    @Mapping(target = "wordBook", ignore = true)
    @Mapping(target = "isUsing", ignore = true)
    @Mapping(target = "id", ignore = true)
    Word createDTOToEntity(CreateWordDTO createWordDTO);
}
