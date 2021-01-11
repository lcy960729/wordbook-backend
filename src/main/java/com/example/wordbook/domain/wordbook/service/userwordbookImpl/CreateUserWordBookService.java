package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateUserWordBookService implements CreateWordBookService {
    private final WordBookRepository wordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    public CreateUserWordBookService(WordBookRepository wordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.wordBookRepository = wordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }

    public Long create(@Valid CreateWordBookDTO createUserWordBookDTO) {
        UserWordBook userWordBook = userWordBookMapper.createUserWordBookDTOToEntity(createUserWordBookDTO);
        userWordBook.setIsUsing(true);

        userWordBook = wordBookRepository.save(userWordBook);

        return userWordBook.getId();
    }
}
