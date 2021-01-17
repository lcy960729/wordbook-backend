package com.example.wordbook.domain.wordbook.service.groupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordToStudyGroupWordDetailDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class GetStudyGroupWordBookService {

    private final GetStudyService studyService;
    private final WordBookRepository wordBookRepository;
    private final StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper;

    public GetStudyGroupWordBookService(GetStudyService studyService, WordBookRepository wordBookRepository, StudyGroupWordToStudyGroupWordDetailDtoMapper studyGroupWordToStudyGroupWordDetailDTOMapper) {
        this.studyService = studyService;
        this.wordBookRepository = wordBookRepository;
        this.studyGroupWordToStudyGroupWordDetailDTOMapper = studyGroupWordToStudyGroupWordDetailDTOMapper;
    }

    public WordBookDetailDTO getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        StudyGroupWordBook studyGroupWordBook = getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        return studyGroupWordToStudyGroupWordDetailDTOMapper.entityToResponseDetailDTO(userId, studyGroupWordBook);
    }

    public StudyGroupWordBook getEntityByUserIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        Study study = studyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        return study.getStudyGroup().getStudyGroupWordBookList().stream()
                .filter(wordBook -> wordBook.getId() == wordBookId.longValue())
                .findFirst()
                .orElseThrow(NotFoundWordBookException::new);
    }

    public StudyGroupWordBook getEntityByAdminIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        Study study = studyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        if (!study.isAdmin()) {
            throw new BusinessException(ErrorCode.HAS_NOT_ADMIN_PERMISSION_TO_ACCESS_STUDYGROUP_WORDBOOK);
        }

        return study.getStudyGroup().getStudyGroupWordBookList().stream()
                .filter(wordBook -> wordBook.getId() == wordBookId.longValue())
                .findFirst()
                .orElseThrow(NotFoundWordBookException::new);
    }

    public StudyGroupWordBook getEntityById(@NotNull Long id) {
        return (StudyGroupWordBook) wordBookRepository.findById(id).orElseThrow(NotFoundWordBookException::new);
    }
}
