package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.mapper.UserWordBookMapper;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateUserWordBookService {
    private final UserWordBookRepository userWordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    public CreateUserWordBookService(UserWordBookRepository userWordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }

    public Long create(@Valid UserWordBookRequestDTO.Create createUserWordBookDTO) {
        UserWordBook userWordBook = userWordBookMapper.createUserWordBookDTOToEntity(createUserWordBookDTO);
        userWordBook.setIsUsing(true);

        userWordBook = userWordBookRepository.save(userWordBook);

        return userWordBook.getId();
    }
}
