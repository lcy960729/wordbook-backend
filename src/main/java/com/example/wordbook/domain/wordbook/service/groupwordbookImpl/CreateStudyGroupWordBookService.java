package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateStudyGroupWordBookService implements CreateWordBookService {

    private final WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public CreateStudyGroupWordBookService(WordBookRepository wordBookRepository) {
        this.wordBookRepository = wordBookRepository;
    }

    public Long create(@Valid CreateWordBookDTO createGroupWordBookDTO) {
        StudyGroupWordBook studyGroupWordBook = modelMapper.map(createGroupWordBookDTO, StudyGroupWordBook.class);

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        return studyGroupWordBook.getId();
    }
}
