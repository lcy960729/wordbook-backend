package com.example.wordbook.domain.word.service.studygroupwordbook;

import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.mapper.WordOfUserWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateWordOfStudyGroupWordBookService {
    private final WordRepository wordRepository;
    private final GetWordOfStudyGroupWordBookService getWordOfStudyGroupWordBookService;

    private final WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper;

    public UpdateWordOfStudyGroupWordBookService(WordRepository wordRepository, GetWordOfStudyGroupWordBookService getWordOfStudyGroupWordBookService, WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper) {
        this.wordRepository = wordRepository;
        this.getWordOfStudyGroupWordBookService = getWordOfStudyGroupWordBookService;
        this.wordOfStudyGroupWordBookToWordDetailDtoMapper = wordOfStudyGroupWordBookToWordDetailDtoMapper;
    }

    public WordDetailDTO update(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId, @Valid UpdateWordDTO updateWordDTO) {
        Word word = getWordOfStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(userId, studyGroupId, wordBookId, wordId);
        word.setVoca(updateWordDTO.getVoca());
        word.setMeaning(updateWordDTO.getMeaning());
        word = wordRepository.save(word);

        return wordOfStudyGroupWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, studyGroupId, wordBookId);
    }
}
