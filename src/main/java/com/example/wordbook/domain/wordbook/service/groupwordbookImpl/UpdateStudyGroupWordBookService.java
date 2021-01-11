package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateStudyGroupWordBookService implements UpdateWordBookService<WordBookDetailDTO, UpdateWordBookDTO> {

    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    private final WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UpdateStudyGroupWordBookService(GetStudyGroupWordBookService getStudyGroupWordBookService, WordBookRepository wordBookRepository) {
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
        this.wordBookRepository = wordBookRepository;
    }

    public WordBookDetailDTO update_name(Long id, @Valid UpdateWordBookDTO updateGroupWordBookDTO) {
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityById(id);

        studyGroupWordBook.setName(updateGroupWordBookDTO.getName());

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        return modelMapper.map(studyGroupWordBook, WordBookDetailDTO.class);
    }
}
