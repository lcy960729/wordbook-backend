package com.example.wordbook.domain.wordbook.service.studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.service.StudyGroupServiceTest;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.mapper.CreateDtoToUserWordBookMapper;
import com.example.wordbook.domain.wordbook.mapper.UserWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.userwordbook.CreateUserWordBookService;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class CreateStudyGroupWordBookServiceTest extends StudyGroupWordBookServiceTest {
    @Mock
    private WordBookRepository wordBookRepository;

    @Mock
    private GetStudyService getStudyService;

    @Test
    @DisplayName("정상적으로 그룹 단어장을 생성하는 테스트")
    public void createStudyGroupWordBook() {
        //given
        CreateStudyGroupWordBookService createStudyGroupWordBookService = new CreateStudyGroupWordBookService(
                wordBookRepository,
                studyGroupWordBookToWordBookDetailDtoMapper,
                getStudyService,
                createDtoToStudyGroupWordBookMapper);

        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO.builder()
                .name("testWordBook")
                .build();

        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();
        studyGroupWordBook.setName(createWordBookDTO.getName());

        Study study = domainFactory.getStudyOfStudyGroupAdmin();

        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);
        given(wordBookRepository.save(any(StudyGroupWordBook.class))).willReturn(studyGroupWordBook);

        //when
        Long adminId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();

        WordBookDetailDTO wordBookDetailDTO = createStudyGroupWordBookService.create(adminId, studyGroupId, createWordBookDTO);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getName()).isEqualTo(createWordBookDTO.getName());
    }
}