package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateGroupWordBookService implements CreateWordBookService {

    private final WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public CreateGroupWordBookService(WordBookRepository wordBookRepository) {
        this.wordBookRepository = wordBookRepository;
    }

    public Long create(@Valid WordBookRequestDTO.Create createGroupWordBookDTO) {
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);

        groupWordBook = wordBookRepository.save(groupWordBook);

        return groupWordBook.getId();
    }
}
