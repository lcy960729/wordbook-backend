package com.example.wordbook.domain.wordbook.service.groupwordbook;

import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordToStudyGroupWordDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class UpdateStudyGroupWordBookService {

    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    private final WordBookRepository wordBookRepository;

    private final StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper;

    public UpdateStudyGroupWordBookService(GetStudyGroupWordBookService getStudyGroupWordBookService, WordBookRepository wordBookRepository, StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper) {
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordToStudyGroupWordDetailDTOMapper = studyGroupWordToStudyGroupWordDetailDTOMapper;
    }

    public WordBookDetailDTO update_name(@NotNull Long adminId, @NotNull Long studyGroupId, @NotNull Long wordBookId, @Valid UpdateWordBookDTO updateGroupWordBookDTO) {
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityByAdminIdAndStudyGroupIdAndWordBookId(adminId, studyGroupId, wordBookId);

        studyGroupWordBook.setName(updateGroupWordBookDTO.getName());

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        return studyGroupWordToStudyGroupWordDetailDTOMapper.entityToResponseDetailDTO(adminId, studyGroupWordBook);
    }
}
