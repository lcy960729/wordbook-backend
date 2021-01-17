package com.example.wordbook.domain.word.service;

import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.mapper.WordOfUserWordBookToWordDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateWordService {
    private final WordRepository wordRepository;
    private final GetWordService getWordService;

    private final WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper;
    private final WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper;

    public UpdateWordService(WordRepository wordRepository, WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper, GetWordService getWordService, WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper) {
        this.wordRepository = wordRepository;
        this.wordOfUserWordBookToWordDetailDtoMapper = wordOfUserWordBookToWordDetailDtoMapper;
        this.getWordService = getWordService;
        this.wordOfStudyGroupWordBookToWordDetailDtoMapper = wordOfStudyGroupWordBookToWordDetailDtoMapper;
    }

    public WordDetailDTO updateAtUserWordBook(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId, @Valid UpdateWordDTO updateWordDTO) {
        Word word = getWordService.getEntityByUserIdAndWordBookIdAndWordId(userId, wordBookId, wordId);
        word = update(word, updateWordDTO);

        return wordOfUserWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, wordBookId);
    }

    public WordDetailDTO updateAtStudyGroupWordBook(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId, @Valid UpdateWordDTO updateWordDTO) {
        Word word = getWordService.getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(userId, studyGroupId, wordBookId, wordId);
        word = update(word, updateWordDTO);

        return wordOfStudyGroupWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, studyGroupId, wordBookId);
    }

    private Word update(Word word, UpdateWordDTO updateWordDTO) {
        word.setVoca(updateWordDTO.getVoca());
        word.setMeaning(updateWordDTO.getMeaning());
        word = wordRepository.save(word);

        return word;
    }
}
