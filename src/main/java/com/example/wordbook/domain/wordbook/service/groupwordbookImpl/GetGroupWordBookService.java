package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import com.example.wordbook.domain.wordbook.exception.WordBookNotFoundException;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GetGroupWordBookService implements GetWordBookService {

    private final WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper;

    public GetGroupWordBookService(WordBookRepository wordBookRepository, ModelMapper modelMapper) {
        this.wordBookRepository = wordBookRepository;
        this.modelMapper = modelMapper;
    }

    public WordBookResponseDTO.Detail getDetailDTOById(Long id) {
        return modelMapper.map(getEntityById(id), WordBookResponseDTO.Detail.class);
    }

    public GroupWordBook getEntityById(Long id) {
        return (GroupWordBook) wordBookRepository.findById(id).orElseThrow(() -> new WordBookNotFoundException(id.toString()));
    }
}
