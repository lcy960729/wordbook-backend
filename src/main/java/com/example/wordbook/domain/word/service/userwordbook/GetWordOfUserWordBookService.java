package com.example.wordbook.domain.word.service.userwordbook;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class GetWordOfUserWordBookService {
    private final GetUserWordBookService getUserWordBookService;


    public GetWordOfUserWordBookService(GetUserWordBookService getUserWordBookService) {
        this.getUserWordBookService = getUserWordBookService;
    }

    public Word getEntityByUserIdAndWordBookIdAndWordId(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        return wordBook.getWordById(wordId);
    }
}
