package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.global.enums.DomainLink;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyGroupWordToStudyGroupWordDetailDtoMapper {
    @Mapping(target = "wordDTOList", source = "studyGroupWordBook.words", qualifiedByName = "mapToWordDTOList")
    WordBookDetailDTO entityToResponseDetailDTO(Long userId, StudyGroupWordBook studyGroupWordBook);

    @Named("mapToWordDTOList")
    default List<WordBookDetailDTO.WordDTO> mapToWordDTOList(List<Word> words) {
        List<WordBookDetailDTO.WordDTO> wordDTOList = new ArrayList<>();
        for (Word word : words) {
            wordDTOList.add(new WordBookDetailDTO.WordDTO(word.getId(), word.getVoca(), word.getMeaning()));
        }
        return wordDTOList;
    }

    @AfterMapping
    default WordBookDetailDTO makeLinks(@MappingTarget WordBookDetailDTO wordBookDetailDTO, Long userId, StudyGroupWordBook studyGroupWordBook) {
        StudyGroup studyGroup = studyGroupWordBook.getStudyGroup();
        boolean isAdmin = studyGroup.getAdmin().getId().equals(userId);

        wordBookDetailDTO.makeLinks(userId, studyGroup.getId(), isAdmin);

        return wordBookDetailDTO;
    }
}
