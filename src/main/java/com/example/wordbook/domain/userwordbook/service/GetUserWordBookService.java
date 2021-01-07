package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.exception.WordBookNotFoundException;
import com.example.wordbook.domain.userwordbook.mapper.UserWordBookMapper;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GetUserWordBookService {

    private final UserWordBookRepository userWordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    public GetUserWordBookService(UserWordBookRepository userWordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }

    public UserWordBookResponseDTO.Detail getDetailDTOById(Long id) {
        return userWordBookMapper.userWordBookToUserWordBookDetailDTO(this.getEntityById(id));
    }

    public UserWordBook getEntityById(Long id) {
        return userWordBookRepository.findById(id).orElseThrow(() -> new WordBookNotFoundException(id.toString()));
    }
}
