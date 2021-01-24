package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class GetUserWordBookService {

    private final UserWordBookRepository userWordBookRepository;
    private final UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper;

    public GetUserWordBookService(UserWordBookRepository userWordBookRepository, UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper) {
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookToWordBookDetailDtoMapper = userWordBookToWordBookDetailDtoMapper;
    }

    public WordBookDetailDTO getDetailDTOByUserIdAndWordBookId(@NotNull Long userId, @NotNull Long wordBookId) {
        return userWordBookToWordBookDetailDtoMapper.entityToResponseDetailDTO(getEntityByUserIdAndWordBookId(userId, wordBookId));
    }

    public UserWordBook getEntityByUserIdAndWordBookId(@NotNull Long userId, @NotNull Long wordBookId) {
        return userWordBookRepository.findByIdAndUserId(wordBookId, userId)
                .orElseThrow(NotFoundWordBookException::new);
    }
}
