package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.StudyGroupWordBookRepository;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.global.mapper.StudyGroupWordBookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetStudyGroupWordBookService {

    private final GetStudyService studyService;
    private final WordBookRepository wordBookRepository;
    private final StudyGroupWordBookMapper studyGroupWordBookMapper;

    public GetStudyGroupWordBookService(GetStudyService studyService, WordBookRepository wordBookRepository, StudyGroupWordBookMapper studyGroupWordBookMapper) {
        this.studyService = studyService;
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordBookMapper = studyGroupWordBookMapper;
    }

    public WordBookDetailDTO getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(Long userId, Long studyGroupId, Long wordBookId) throws Exception {
        StudyGroupWordBook studyGroupWordBook = getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        return studyGroupWordBookMapper.entityToResponseDetailDTO(userId, studyGroupWordBook);
    }

    public StudyGroupWordBook getEntityByUserIdAndStudyGroupIdAndWordBookId(Long userId, Long studyGroupId, Long wordBookId) {
        Study study = studyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        return study.getStudyGroup().getStudyGroupWordBookList().stream()
                .filter(wordBook -> wordBook.getId() == wordBookId.longValue())
                .findFirst().orElseThrow(NotFoundWordBookException::new);
    }

    public StudyGroupWordBook getEntityById(Long id) {
        return (StudyGroupWordBook) wordBookRepository.findById(id).orElseThrow(NotFoundWordBookException::new);
    }
}
