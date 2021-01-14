package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import com.example.wordbook.global.mapper.StudyGroupWordBookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UpdateStudyGroupWordBookService {

    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    private final WordBookRepository wordBookRepository;

    private final StudyGroupWordBookMapper studyGroupWordBookMapper;

    public UpdateStudyGroupWordBookService(GetStudyGroupWordBookService getStudyGroupWordBookService, WordBookRepository wordBookRepository, StudyGroupWordBookMapper studyGroupWordBookMapper) {
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordBookMapper = studyGroupWordBookMapper;
    }

    public WordBookDetailDTO update_name(Long userId, Long studyGroupId, Long wordBookId, @Valid UpdateWordBookDTO updateGroupWordBookDTO) throws Exception {
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        studyGroupWordBook.setName(updateGroupWordBookDTO.getName());

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        return studyGroupWordBookMapper.entityToResponseDetailDTO(userId, studyGroupWordBook);
    }
}
