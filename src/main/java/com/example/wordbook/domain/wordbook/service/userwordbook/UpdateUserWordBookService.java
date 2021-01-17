package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.UserWordBookRepository;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateUserWordBookService {

    private final GetUserWordBookService getUserWordBookService;

    private final UserWordBookRepository userWordBookRepository;

    private final UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper;

    public UpdateUserWordBookService(GetUserWordBookService getUserWordBookService, UserWordBookRepository userWordBookRepository, UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper) {
        this.getUserWordBookService = getUserWordBookService;
        this.userWordBookRepository = userWordBookRepository;
        this.userWordBookToWordBookDetailDtoMapper = userWordBookToWordBookDetailDtoMapper;
    }


    public WordBookDetailDTO update_name(@NotNull Long userId, @NotNull Long wordBookId, @Valid UpdateWordBookDTO updateUserWordBookDTO) {
        UserWordBook userWordBook = getUserWordBookService.getEntityByUserIdAndWordBookId(userId, wordBookId);

        userWordBook.setName(updateUserWordBookDTO.getName());

        userWordBook = userWordBookRepository.save(userWordBook);

        return userWordBookToWordBookDetailDtoMapper.entityToResponseDetailDTO(userWordBook);
    }
}
