package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class DeleteUserWordBookService {

    private final UserWordBookRepository userWordBookRepository;
    private final GetUserWordBookService getUserWordBookService;

    public DeleteUserWordBookService(UserWordBookRepository userWordBookRepository, GetUserWordBookService getUserWordBookService) {
        this.userWordBookRepository = userWordBookRepository;
        this.getUserWordBookService = getUserWordBookService;
    }

    public void deleteById(@NotNull Long userId, @NotNull Long wordBookId) {
        UserWordBook userWordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);

        userWordBookRepository.delete(userWordBook);
    }
}
