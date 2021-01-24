package com.example.wordbook.domain.word.service.studygroupwordbook;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DeleteWordOfStudyGroupWordBookService {
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public DeleteWordOfStudyGroupWordBookService(GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public void delete(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @NotNull Long wordId) {
        WordBook wordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);
        wordBook.deleteWord(wordId);
    }

}
