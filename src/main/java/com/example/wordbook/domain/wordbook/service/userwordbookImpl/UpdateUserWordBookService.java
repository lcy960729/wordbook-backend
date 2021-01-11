package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateUserWordBookService implements UpdateWordBookService<WordBookDetailDTO, UpdateWordBookDTO> {

    private final GetUserWordBookService getUserWordBookService;

    private final UserWordBookRepository userWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UpdateUserWordBookService(GetUserWordBookService getUserWordBookService, UserWordBookRepository userWordBookRepository) {
        this.getUserWordBookService = getUserWordBookService;
        this.userWordBookRepository = userWordBookRepository;
    }


    public WordBookDetailDTO update_name(Long id, @Valid UpdateWordBookDTO updateUserWordBookDTO) {
        UserWordBook userWordBook = getUserWordBookService.getEntityById(id);

        userWordBook.setName(updateUserWordBookDTO.getName());

        userWordBook = userWordBookRepository.save(userWordBook);

        return modelMapper.map(userWordBook, WordBookDetailDTO.class);
    }
}
