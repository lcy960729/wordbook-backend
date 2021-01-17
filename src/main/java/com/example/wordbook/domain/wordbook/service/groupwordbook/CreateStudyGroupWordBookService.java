package com.example.wordbook.domain.wordbook.service.groupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToStudyGroupWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordToStudyGroupWordDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class CreateStudyGroupWordBookService {

    private final WordBookRepository wordBookRepository;
    private final StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper;
    private final CreateDtoToStudyGroupWordBookMapper createDtoToStudyGroupWordBookMapper;

    private final GetStudyService getStudyService;

    public CreateStudyGroupWordBookService(WordBookRepository wordBookRepository, StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper, GetStudyService getStudyService, CreateDtoToStudyGroupWordBookMapper createDtoToStudyGroupWordBookMapper) {
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordToStudyGroupWordDetailDTOMapper = studyGroupWordToStudyGroupWordDetailDTOMapper;
        this.getStudyService = getStudyService;
        this.createDtoToStudyGroupWordBookMapper = createDtoToStudyGroupWordBookMapper;
    }

    public WordBookDetailDTO create(@NotNull Long adminId, @NotNull Long studyGroupId, @Valid CreateWordBookDTO createGroupWordBookDTO) {
        Study study = getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(adminId, studyGroupId);

        StudyGroupWordBook studyGroupWordBook = createDtoToStudyGroupWordBookMapper.createDTOTOEntity(createGroupWordBookDTO);
        studyGroupWordBook.use();
        studyGroupWordBook.setStudyGroup(study.getStudyGroup());

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        study.getStudyGroup().addWordBook(studyGroupWordBook);


        return studyGroupWordToStudyGroupWordDetailDTOMapper.entityToResponseDetailDTO(adminId, studyGroupWordBook);
    }
}
