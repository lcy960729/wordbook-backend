package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import org.springframework.stereotype.Service;

@Service
public class DeleteGroupWordBookService implements DeleteWordBookService {

    private final WordBookRepository wordBookRepository;
    private final GetGroupWordBookService getGroupWordBookService;

    public DeleteGroupWordBookService(WordBookRepository wordBookRepository, GetGroupWordBookService getGroupWordBookService) {
        this.wordBookRepository = wordBookRepository;
        this.getGroupWordBookService = getGroupWordBookService;
    }

    public void deleteById(Long id){
        GroupWordBook groupWordBook = getGroupWordBookService.getEntityById(id);

        wordBookRepository.delete(groupWordBook);
    }
}
