package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateGroupWordBookService {

    private final GroupWordBookRepository groupWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public CreateGroupWordBookService(GroupWordBookRepository groupWordBookRepository) {
        this.groupWordBookRepository = groupWordBookRepository;
    }

    public Long create(@Valid GroupWordBookRequestDTO.Create createGroupWordBookDTO) {
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);

        groupWordBook = groupWordBookRepository.save(groupWordBook);

        return groupWordBook.getId();
    }
}
