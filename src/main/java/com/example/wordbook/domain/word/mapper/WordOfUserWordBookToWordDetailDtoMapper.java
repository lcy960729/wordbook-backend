package com.example.wordbook.domain.word.mapper;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WordOfUserWordBookToWordDetailDtoMapper {
    WordDetailDTO entityToDetailDTO(Word word, Long userId, Long wordBookId);

    @AfterMapping
    default WordDetailDTO makeLinks(@MappingTarget WordDetailDTO wordDetailDTO, Long userId, Long wordBookId) {
        wordDetailDTO.makeLinks(userId, wordBookId, wordDetailDTO.getId());

        return wordDetailDTO;
    }
}
