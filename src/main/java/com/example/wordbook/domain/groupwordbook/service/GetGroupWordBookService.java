package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.groupwordbook.exception.WordBookNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GetGroupWordBookService {

    private final GroupWordBookRepository groupWordBookRepository;

    private final ModelMapper modelMapper;

    public GetGroupWordBookService(GroupWordBookRepository groupWordBookRepository, ModelMapper modelMapper) {
        this.groupWordBookRepository = groupWordBookRepository;
        this.modelMapper = modelMapper;
    }

    public GroupWordBookResponseDTO.Detail getDetailDTOById(Long id) {
        GroupWordBook groupWordBook = groupWordBookRepository.findById(id).orElseThrow(() -> new WordBookNotFoundException(id.toString()));

        return modelMapper.map(groupWordBook, GroupWordBookResponseDTO.Detail.class);
    }

    public GroupWordBook getEntityById(Long id) {
        return groupWordBookRepository.findById(id).orElseThrow(() -> new WordBookNotFoundException(id.toString()));
    }
}
