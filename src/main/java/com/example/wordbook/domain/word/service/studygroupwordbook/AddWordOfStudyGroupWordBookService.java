package com.example.wordbook.domain.word.service.studygroupwordbook;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.mapper.CreateDtoToWordMapper;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class AddWordOfStudyGroupWordBookService {

    private final WordRepository wordRepository;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    private final CreateDtoToWordMapper createDtoToWordMapper;
    private final WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper;

    public AddWordOfStudyGroupWordBookService(WordRepository wordRepository, GetStudyGroupWordBookService getStudyGroupWordBookService, WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper, CreateDtoToWordMapper createDtoToWordMapper) {
        this.wordRepository = wordRepository;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
        this.wordOfStudyGroupWordBookToWordDetailDtoMapper = wordOfStudyGroupWordBookToWordDetailDtoMapper;
        this.createDtoToWordMapper = createDtoToWordMapper;
    }


    public WordDetailDTO add(@NotNull Long userId, @NotNull Long studyGroup, @NotNull Long wordBookId, @Valid CreateWordDTO createWordDTO) {
        WordBook wordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroup, wordBookId);
        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);
        word.use();
        word.setWordBook(wordBook);

        word = wordRepository.save(word);

        wordBook.addWord(word);
        return wordOfStudyGroupWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, studyGroup, wordBookId);
    }
}
