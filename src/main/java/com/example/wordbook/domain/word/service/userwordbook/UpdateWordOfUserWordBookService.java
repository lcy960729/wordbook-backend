package com.example.wordbook.domain.word.service.userwordbook;

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
public class UpdateWordOfUserWordBookService {
    private final WordRepository wordRepository;
    private final GetWordOfUserWordBookService getWordOfUserWordBookService;

    private final WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper;

    public UpdateWordOfUserWordBookService(WordRepository wordRepository, WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper, GetWordOfUserWordBookService getWordOfUserWordBookService) {
        this.wordRepository = wordRepository;
        this.wordOfUserWordBookToWordDetailDtoMapper = wordOfUserWordBookToWordDetailDtoMapper;
        this.getWordOfUserWordBookService = getWordOfUserWordBookService;
    }

    public WordDetailDTO update(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId, @Valid UpdateWordDTO updateWordDTO) {
        Word word = getWordOfUserWordBookService.getEntityByUserIdAndWordBookIdAndWordId(userId, wordBookId, wordId);
        word.setVoca(updateWordDTO.getVoca());
        word.setMeaning(updateWordDTO.getMeaning());
        word = wordRepository.save(word);

        return wordOfUserWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, wordBookId);
    }
}
