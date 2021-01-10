package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("deleteUserWordBookService")
public class DeleteUserWordBookService implements DeleteWordBookService {

    private final UserWordBookRepository userWordBookRepository;

    private final GetUserWordBookService getUserWordBookService;

    public DeleteUserWordBookService(UserWordBookRepository userWordBookRepository, GetUserWordBookService getUserWordBookService) {
        this.userWordBookRepository = userWordBookRepository;
        this.getUserWordBookService = getUserWordBookService;
    }

    public void deleteById(Long id){
        UserWordBook userWordBook = getUserWordBookService.getEntityById(id);

        userWordBookRepository.delete(userWordBook);
    }
}
