package com.example.wordbook.domain.wordbook.service.userwordbook;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToUserWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class CreateUserWordBookService {
    private final WordBookRepository wordBookRepository;
    private final UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper;
    private final CreateDtoToUserWordBookMapper createDtoToUserWordBookMapper;

    private final GetUserService getUserService;

    public CreateUserWordBookService(WordBookRepository wordBookRepository, UserWordBookToWordBookDetailDtoMapper userWordBookToWordBookDetailDtoMapper, GetUserService getUserService, CreateDtoToUserWordBookMapper createDtoToUserWordBookMapper) {
        this.wordBookRepository = wordBookRepository;
        this.userWordBookToWordBookDetailDtoMapper = userWordBookToWordBookDetailDtoMapper;
        this.getUserService = getUserService;
        this.createDtoToUserWordBookMapper = createDtoToUserWordBookMapper;
    }

    public WordBookDetailDTO create(@NotNull Long userId, @Valid CreateWordBookDTO createUserWordBookDTO) {
        UserWordBook userWordBook = createDtoToUserWordBookMapper.createDTOToEntity(createUserWordBookDTO);
        userWordBook.setIsUsing(true);

        User user = getUserService.getEntityById(userId);
        userWordBook.setUser(user);

        userWordBook = wordBookRepository.save(userWordBook);

        return userWordBookToWordBookDetailDtoMapper.entityToResponseDetailDTO(userWordBook);
    }
}
