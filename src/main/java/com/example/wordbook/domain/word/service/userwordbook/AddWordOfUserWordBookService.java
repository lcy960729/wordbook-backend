package com.example.wordbook.domain.word.service.userwordbook;

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
public class AddWordOfUserWordBookService {

    private final WordRepository wordRepository;
    private final GetUserWordBookService getUserWordBookService;
    private final CreateDtoToWordMapper createDtoToWordMapper;
    private final WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper;

    public AddWordOfUserWordBookService(WordRepository wordRepository, WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper, GetUserWordBookService getUserWordBookService, CreateDtoToWordMapper createDtoToWordMapper) {
        this.wordRepository = wordRepository;
        this.wordOfUserWordBookToWordDetailDtoMapper = wordOfUserWordBookToWordDetailDtoMapper;
        this.getUserWordBookService = getUserWordBookService;
        this.createDtoToWordMapper = createDtoToWordMapper;
    }

    public WordDetailDTO add(@NotNull Long userId, @NotNull Long wordBookId, @Valid CreateWordDTO createWordDTO) {
        WordBook wordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);
        word.use();
        word.setWordBook(wordBook);

        word = wordRepository.save(word);

        wordBook.addWord(word);

        return wordOfUserWordBookToWordDetailDtoMapper.entityToDetailDTO(word, userId, wordBookId);
    }
}
