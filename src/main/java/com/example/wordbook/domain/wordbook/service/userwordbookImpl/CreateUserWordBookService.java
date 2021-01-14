package com.example.wordbook.domain.wordbook.service.userwordbookImpl;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateUserWordBookService {
    private final WordBookRepository wordBookRepository;
    private final UserWordBookMapper userWordBookMapper;

    private final GetUserService getUserService;

    public CreateUserWordBookService(WordBookRepository wordBookRepository, UserWordBookMapper userWordBookMapper, GetUserService getUserService) {
        this.wordBookRepository = wordBookRepository;
        this.userWordBookMapper = userWordBookMapper;
        this.getUserService = getUserService;
    }

    public WordBookDetailDTO create(Long userId, @Valid CreateWordBookDTO createUserWordBookDTO) throws Exception {
        UserWordBook userWordBook = userWordBookMapper.createDTOToEntity(createUserWordBookDTO);
        userWordBook.setIsUsing(true);

        User user = getUserService.getEntityById(userId);
        userWordBook.setUser(user);

        userWordBook = wordBookRepository.save(userWordBook);

        return userWordBookMapper.entityToResponseDetailDTO(userWordBook);
    }
}
