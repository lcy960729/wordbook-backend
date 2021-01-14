package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateUserWordBookService {

    private final GetUserWordBookService getUserWordBookService;

    private final UserWordBookRepository userWordBookRepository;

    private final UserWordBookMapper userWordBookMapper;

    public UpdateUserWordBookService(GetUserWordBookService getUserWordBookService, UserWordBookRepository userWordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.getUserWordBookService = getUserWordBookService;
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }


    public WordBookDetailDTO update_name(Long userId, Long wordBookId, @Valid UpdateWordBookDTO updateUserWordBookDTO) throws Exception {
        UserWordBook userWordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);

        userWordBook.setName(updateUserWordBookDTO.getName());

        userWordBook = userWordBookRepository.save(userWordBook);

        return userWordBookMapper.entityToResponseDetailDTO(userWordBook);
    }
}
