package com.example.wordbook.domain.word.service.userwordbook;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DeleteWordOfUserWordBookService {
    private final GetUserWordBookService getUserWordBookService;

    public DeleteWordOfUserWordBookService(GetUserWordBookService getUserWordBookService ) {
        this.getUserWordBookService = getUserWordBookService;
    }

    public void delete(@NotNull Long userId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);
        wordBook.deleteWord(wordId);
    }
}
