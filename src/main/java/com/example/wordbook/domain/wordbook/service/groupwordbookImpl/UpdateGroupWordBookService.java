package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateGroupWordBookService implements UpdateWordBookService<WordBookResponseDTO.Detail, WordBookRequestDTO.Update> {

    private final GetGroupWordBookService getGroupWordBookService;

    private final WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UpdateGroupWordBookService(GetGroupWordBookService getGroupWordBookService, WordBookRepository wordBookRepository) {
        this.getGroupWordBookService = getGroupWordBookService;
        this.wordBookRepository = wordBookRepository;
    }

    public WordBookResponseDTO.Detail update_name(Long id, @Valid WordBookRequestDTO.Update updateGroupWordBookDTO) {
        GroupWordBook groupWordBook = getGroupWordBookService.getEntityById(id);

        groupWordBook.setName(updateGroupWordBookDTO.getName());

        groupWordBook = wordBookRepository.save(groupWordBook);

        return modelMapper.map(groupWordBook, WordBookResponseDTO.Detail.class);
    }
}
