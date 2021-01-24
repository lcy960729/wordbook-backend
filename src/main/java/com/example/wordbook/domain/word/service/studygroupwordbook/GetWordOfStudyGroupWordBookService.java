package com.example.wordbook.domain.word.service.studygroupwordbook;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class GetWordOfStudyGroupWordBookService {
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public GetWordOfStudyGroupWordBookService(GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public Word getEntityByUserIdAndStudyGroupIdAndWordBookIdAndWordId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);
        return wordBook.getWordById(wordId);
    }
}
