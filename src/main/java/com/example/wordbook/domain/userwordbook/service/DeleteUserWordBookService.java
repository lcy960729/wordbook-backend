package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserWordBookService {

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
