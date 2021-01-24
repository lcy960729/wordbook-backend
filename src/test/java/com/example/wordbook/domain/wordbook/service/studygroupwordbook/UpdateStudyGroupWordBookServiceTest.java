package com.example.wordbook.domain.wordbook.service.studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.springframework.test.context.junit.jupiter.SpringExtension;

public class UpdateStudyGroupWordBookServiceTest extends StudyGroupWordBookServiceTest {
    @Mock
    private WordBookRepository wordBookRepository;

    @Mock
    private GetStudyGroupWordBookService getStudyGroupWordBookService;

    @Test
    @DisplayName("정상적으로 그룹 단어장을 수정하는 테스트")
    public void updateStudyGroupWordBook() {
        //given
        UpdateStudyGroupWordBookService updateStudyGroupWordBookService = new UpdateStudyGroupWordBookService(
                getStudyGroupWordBookService,
                wordBookRepository,
                studyGroupWordBookToWordBookDetailDtoMapper);

        UpdateWordBookDTO updateWordBookDTO = UpdateWordBookDTO.builder()
                .name("UpdateTestWordBookName")
                .build();

        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();
        studyGroupWordBook.setName(updateWordBookDTO.getName());

        Study study = domainFactory.getStudyOfStudyGroupAdmin();

        given(getStudyGroupWordBookService.getEntityByAdminIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong())).willReturn(studyGroupWordBook);
        given(wordBookRepository.save(any(StudyGroupWordBook.class))).willReturn(studyGroupWordBook);

        //when
        Long adminId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = studyGroupWordBook.getId();

        WordBookDetailDTO wordBookDetailDTO = updateStudyGroupWordBookService.update_name(adminId, studyGroupId, wordBookId, updateWordBookDTO);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getName()).isEqualTo(updateWordBookDTO.getName());
    }
}