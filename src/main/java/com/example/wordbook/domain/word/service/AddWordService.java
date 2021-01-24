package com.example.wordbook.domain.word.service;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import com.example.wordbook.domain.word.mapper.CreateDtoToWordMapper;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.mapper.WordOfUserWordBookToWordDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class AddWordService {

    private final WordRepository wordRepository;
    private final GetUserWordBookService userWordBookService;
    private final GetStudyGroupWordBookService studyGroupWordBookService;

    private final CreateDtoToWordMapper createDtoToWordMapper;
    private final WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper;
    private final WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper;

    public AddWordService(WordRepository wordRepository, WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper, GetUserWordBookService userWordBookService, GetStudyGroupWordBookService studyGroupWordBookService, WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper, CreateDtoToWordMapper createDtoToWordMapper) {
        this.wordRepository = wordRepository;
        this.wordOfUserWordBookToWordDetailDtoMapper = wordOfUserWordBookToWordDetailDtoMapper;
        this.userWordBookService = userWordBookService;
        this.studyGroupWordBookService = studyGroupWordBookService;
        this.wordOfStudyGroupWordBookToWordDetailDtoMapper = wordOfStudyGroupWordBookToWordDetailDtoMapper;
        this.createDtoToWordMapper = createDtoToWordMapper;
    }

    public WordDetailDTO addAtUserWordBook(@NotNull Long userId, @NotNull Long wordBookId, @Valid CreateWordDTO createWordDTO) {
        WordBook wordBook = userWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        Word word = createWord(wordBook, createWordDTO);

        return wordOfUserWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, wordBookId);

    }

    public WordDetailDTO addAtStudyGroupWordBook(@NotNull Long userId, @NotNull Long studyGroup, @NotNull Long wordBookId, @Valid CreateWordDTO createWordDTO) {
        WordBook wordBook = studyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroup, wordBookId);
        Word word = createWord(wordBook, createWordDTO);

        return wordOfStudyGroupWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, studyGroup, wordBookId);
    }

    private Word createWord(WordBook wordBook, CreateWordDTO createWordDTO) {
        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);
        word.use();
        word.setWordBook(wordBook);

        word = wordRepository.save(word);

        wordBook.addWord(word);

        return word;
    }
}
