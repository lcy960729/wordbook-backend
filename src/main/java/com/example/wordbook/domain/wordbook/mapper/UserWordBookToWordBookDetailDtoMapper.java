package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWordBookToWordBookDetailDtoMapper {
    @Mapping(target = "wordDTOList", source = "userWordBook.words", qualifiedByName = "mapToWordDTOList")
    WordBookDetailDTO entityToResponseDetailDTO(UserWordBook userWordBook);

    @Named("mapToWordDTOList")
    default List<WordBookDetailDTO.WordDTO> mapToWordDTOList(List<Word> words) {
        List<WordBookDetailDTO.WordDTO> wordDTOList = new ArrayList<>();
        for (Word word : words) {
            wordDTOList.add(new WordBookDetailDTO.WordDTO(word.getId(), word.getVoca(), word.getMeaning()));
        }
        return wordDTOList;
    }

    @AfterMapping
    default WordBookDetailDTO makeLinks(@MappingTarget WordBookDetailDTO wordBookDetailDTO, UserWordBook userWordBook) {
        wordBookDetailDTO.makeLinks(userWordBook.getUser().getId());

        return wordBookDetailDTO;
    }
}
