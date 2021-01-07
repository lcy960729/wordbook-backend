package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateUserWordBookService {

    private final GetUserWordBookService getUserWordBookService;

    private final UserWordBookRepository userWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UpdateUserWordBookService(GetUserWordBookService getUserWordBookService, UserWordBookRepository userWordBookRepository) {
        this.getUserWordBookService = getUserWordBookService;
        this.userWordBookRepository = userWordBookRepository;
    }


    public UserWordBookResponseDTO.Detail update_name(Long id, @Valid UserWordBookRequestDTO.Update updateUserWordBookDTO) {
        UserWordBook userWordBook = getUserWordBookService.getEntityById(id);

        userWordBook.changeName(updateUserWordBookDTO.getName());

        userWordBook = userWordBookRepository.save(userWordBook);

        return modelMapper.map(userWordBook, UserWordBookResponseDTO.Detail.class);
    }
}
