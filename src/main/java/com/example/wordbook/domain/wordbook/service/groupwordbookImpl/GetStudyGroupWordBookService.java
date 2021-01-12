package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GetStudyGroupWordBookService implements GetWordBookService<WordBookDetailDTO, StudyGroupWordBook> {

    private final WordBookRepository wordBookRepository;
    private final ModelMapper modelMapper;

    public GetStudyGroupWordBookService(WordBookRepository wordBookRepository, ModelMapper modelMapper) {
        this.wordBookRepository = wordBookRepository;
        this.modelMapper = modelMapper;
    }

    public WordBookDetailDTO getDetailDTOById(Long id) {
        return modelMapper.map(getEntityById(id), WordBookDetailDTO.class);
    }

    public StudyGroupWordBook getEntityById(Long id) {
        return (StudyGroupWordBook) wordBookRepository.findById(id).orElseThrow(NotFoundWordBookException::new);
    }
}
