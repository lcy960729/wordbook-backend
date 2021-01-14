package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserWordBookService {

    private final UserWordBookRepository userWordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    public GetUserWordBookService(UserWordBookRepository userWordBookRepository, UserWordBookMapper userWordBookMapper) {
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
    }

    public WordBookDetailDTO getDetailDTOById(Long id) throws Exception {
        return userWordBookMapper.entityToResponseDetailDTO(this.getEntityById(id));
    }

    public WordBookDetailDTO getDetailDTOByUserIdAndWordBookId(Long userId, Long wordBookId) throws Exception {
        return userWordBookMapper.entityToResponseDetailDTO(this.getEntityByUserIdAndWordBookId(userId, wordBookId));
    }

    public UserWordBook getEntityByUserIdAndWordBookId(Long userId, Long wordBookId) {
        return userWordBookRepository.findByIdAndUserId(wordBookId, userId).orElseThrow(NotFoundWordBookException::new);
    }

    public UserWordBook getEntityById(Long id) {
        return userWordBookRepository.findById(id).orElseThrow(NotFoundWordBookException::new);
    }
}
