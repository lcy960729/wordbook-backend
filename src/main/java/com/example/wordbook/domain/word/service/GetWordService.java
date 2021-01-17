package com.example.wordbook.domain.word.service;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.groupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class GetWordService {
    private final GetUserWordBookService getUserWordBookService;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public GetWordService(GetUserWordBookService getUserWordBookService, GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.getUserWordBookService = getUserWordBookService;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public Word getEntityByUserIdAndWordBookIdAndWordId(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        return wordBook.getWordById(wordId);
    }

    public Word getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);
        return wordBook.getWordById(wordId);
    }
}
