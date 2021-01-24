package com.example.wordbook.domain.word.service;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DeleteWordService {
    private final GetUserWordBookService getUserWordBookService;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public DeleteWordService(GetUserWordBookService getUserWordBookService, GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.getUserWordBookService = getUserWordBookService;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public void deleteAtUserWordBook(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        delete(wordId, wordBook);
    }

    public void deleteAtStudyGroupWordBook(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);
        delete(wordId, wordBook);
    }

    private void delete(Long wordId, WordBook wordBook) {
        wordBook.deleteWord(wordId);
    }
}
