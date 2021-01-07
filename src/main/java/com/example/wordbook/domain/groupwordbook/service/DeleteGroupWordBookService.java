package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteGroupWordBookService {

    private final GroupWordBookRepository groupWordBookRepository;

    private final GetGroupWordBookService getGroupWordBookService;

    public DeleteGroupWordBookService(GroupWordBookRepository groupWordBookRepository, GetGroupWordBookService getGroupWordBookService) {
        this.groupWordBookRepository = groupWordBookRepository;
        this.getGroupWordBookService = getGroupWordBookService;
    }

    public void deleteById(Long id){
        GroupWordBook groupWordBook = getGroupWordBookService.getEntityById(id);

        groupWordBookRepository.delete(groupWordBook);
    }
}
