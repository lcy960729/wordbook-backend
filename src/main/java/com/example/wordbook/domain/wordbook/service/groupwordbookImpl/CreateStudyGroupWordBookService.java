package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.global.mapper.StudyGroupWordBookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class CreateStudyGroupWordBookService {

    private final WordBookRepository wordBookRepository;
    private final StudyGroupWordBookMapper studyGroupWordBookMapper;

    private final GetStudyService getStudyService;

    public CreateStudyGroupWordBookService(WordBookRepository wordBookRepository, StudyGroupWordBookMapper studyGroupWordBookMapper, GetStudyService getStudyService) {
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordBookMapper = studyGroupWordBookMapper;
        this.getStudyService = getStudyService;
    }

    public WordBookDetailDTO create(Long userId, Long studyGroupId, @Valid CreateWordBookDTO createGroupWordBookDTO) throws Exception {
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        if (study.getStudyGroupRole() != StudyGroupRole.ADMIN)
            throw new Exception();

        StudyGroupWordBook studyGroupWordBook = studyGroupWordBookMapper.createDTOTOEntity(createGroupWordBookDTO);
        studyGroupWordBook.setIsUsing(true);
        studyGroupWordBook.setStudyGroup(study.getStudyGroup());

        studyGroupWordBook = wordBookRepository.save(studyGroupWordBook);

        study.getStudyGroup().addWordBook(studyGroupWordBook);

        return studyGroupWordBookMapper.entityToResponseDetailDTO(userId, studyGroupWordBook);
    }
}
