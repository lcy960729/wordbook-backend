package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateGroupWordBookService {

    private final GetGroupWordBookService getGroupWordBookService;

    private final GroupWordBookRepository groupWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UpdateGroupWordBookService(GetGroupWordBookService getGroupWordBookService, GroupWordBookRepository groupWordBookRepository) {
        this.getGroupWordBookService = getGroupWordBookService;
        this.groupWordBookRepository = groupWordBookRepository;
    }

    public GroupWordBookResponseDTO.Detail update_name(Long id, @Valid GroupWordBookRequestDTO.Update updateGroupWordBookDTO) {
        GroupWordBook groupWordBook = getGroupWordBookService.getEntityById(id);

        groupWordBook.changeName(updateGroupWordBookDTO.getName());

        groupWordBook = groupWordBookRepository.save(groupWordBook);

        return modelMapper.map(groupWordBook, GroupWordBookResponseDTO.Detail.class);
    }
}
