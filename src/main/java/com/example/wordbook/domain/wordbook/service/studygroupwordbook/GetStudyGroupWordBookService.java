package com.example.wordbook.domain.wordbook.service.studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class GetStudyGroupWordBookService {

    private final GetStudyService getStudyService;
    private final StudyGroupWordBookToWordBookDetailDtoMapper studyGroupWordBookToWordBookDetailDTOMapper;

    public GetStudyGroupWordBookService(GetStudyService getStudyService, StudyGroupWordBookToWordBookDetailDtoMapper studyGroupWordBookToWordBookDetailDTOMapper) {
        this.getStudyService = getStudyService;
        this.studyGroupWordBookToWordBookDetailDTOMapper = studyGroupWordBookToWordBookDetailDTOMapper;
    }

    public WordBookDetailDTO getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        StudyGroupWordBook studyGroupWordBook = getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        return studyGroupWordBookToWordBookDetailDTOMapper.entityToResponseDetailDTO(userId, studyGroupWordBook);
    }

    public StudyGroupWordBook getEntityByUserIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        return study.getStudyGroup().getStudyGroupWordBookList().stream()
                .filter(wordBook -> wordBook.getId() == wordBookId.longValue())
                .findFirst()
                .orElseThrow(NotFoundWordBookException::new);
    }

    public StudyGroupWordBook getEntityByAdminIdAndStudyGroupIdAndWordBookId(@NotNull Long userId, @NotNull Long studyGroupId, @NotNull Long wordBookId) {
        Study study = getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(userId, studyGroupId);

        return study.getStudyGroup().getStudyGroupWordBookList().stream()
                .filter(wordBook -> wordBook.getId() == wordBookId.longValue())
                .findFirst()
                .orElseThrow(NotFoundWordBookException::new);
    }
}
