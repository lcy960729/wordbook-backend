package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserWordBookService implements GetWordBookService<WordBookDetailDTO, UserWordBook> {

    private final UserWordBookRepository userWordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    public GetUserWordBookService(UserWordBookRepository userWordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }

    public WordBookDetailDTO getDetailDTOById(Long id) {
        return userWordBookMapper.entityToUserWordBookDetailDTO(this.getEntityById(id));
    }

    public UserWordBook getEntityById(Long id) {
        return userWordBookRepository.findById(id).orElseThrow(NotFoundWordBookException::new);
    }
}
