package com.example.wordbook.domain.wordbook.service.studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.exception.NotFoundWordBookException;
import com.example.wordbook.domain.wordbook.mapper.StudyGroupWordBookToWordBookDetailDtoMapper;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.StudyGroupWordBookServiceTest;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class GetStudyGroupWordBookServiceTest extends StudyGroupWordBookServiceTest {
    @Mock
    private GetStudyService getStudyService;

    private GetStudyGroupWordBookService getStudyGroupWordBookService;

    @BeforeEach
    void setUp() {
         getStudyGroupWordBookService = new GetStudyGroupWordBookService(
                getStudyService,
                studyGroupWordBookToWordBookDetailDtoMapper
        );
    }

    @Test
    @DisplayName("정상적으로 일반 권한 사용자가 그룹 단어장을 DTO로 정상적으로 반환하는 테스트")
    void getDetailDTOByUserIdAndStudyGroupIdAndWordBookId() {
        //given
        Study study = domainFactory.getStudyOfStudyGroupNormal();
        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        Long userId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = studyGroupWordBook.getId();

        //when
        WordBookDetailDTO wordBookDetailDTO = getStudyGroupWordBookService.getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getId()).isEqualTo(wordBookId);
    }

    @Test
    @DisplayName("일반 권한 사용자에 대한 그룹 단어장을 정상적으로 반환하는 테스트")
    void getEntityByUserIdAndStudyGroupIdAndWordBookId() {
        //given
        Study study = domainFactory.getStudyOfStudyGroupNormal();
        StudyGroupWordBook _studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        Long userId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = _studyGroupWordBook.getId();

        //when
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(userId, studyGroupId, wordBookId);

        //then
        assertThat(studyGroupWordBook).isNotNull();
    }


    @Test
    @DisplayName("가입되지 않은 그룹의 단어장을 불러올시 에러 발생 테스트")
    void getEntityByUserIdAndStudyGroupIdAndWordBookId_NotInvalid_StudyGroupWordBook_ErrorTest() {
        //given
        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(), anyLong())).willThrow(NotFoundWordBookException.class);

        Long invalidUserId = 0L;
        Long invalidStudyGroupId = 0L;
        Long invalidWordBookId = 0L;

        //when
        Throwable throwable = catchThrowable(() ->
                getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(invalidUserId, invalidStudyGroupId, invalidWordBookId));
        //then
        assertThat(throwable).isInstanceOf(NotFoundWordBookException.class);
    }

    @Test
    @DisplayName("관리자 권한 사용자에 대한 그룹 단어장을 정상적으로 반환하는 테스트")
    void getEntityByAdminIdAndStudyGroupIdAndWordBookId() {
        //given
        Study study = domainFactory.getStudyOfStudyGroupAdmin();
        StudyGroupWordBook _studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        Long adminId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = _studyGroupWordBook.getId();

        //when
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityByAdminIdAndStudyGroupIdAndWordBookId(adminId, studyGroupId, wordBookId);

        //then
        assertThat(studyGroupWordBook).isNotNull();
    }


    @Test
    @DisplayName("관리자 권한이 없는 사용자가 단어장을 불러올시 에러 발생 테스트")
    void getEntityByAdminIdAndStudyGroupIdAndWordBookId_NotInvalid_StudyGroupWordBook_ErrorTest() {
        //given
        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willThrow(NotFoundWordBookException.class);

        Long invalidAdminId = 0L;
        Long invalidStudyGroupId = 0L;
        Long invalidWordBookId = 0L;

        //when
        Throwable throwable = catchThrowable(() ->
                getStudyGroupWordBookService.getEntityByAdminIdAndStudyGroupIdAndWordBookId(invalidAdminId, invalidStudyGroupId, invalidWordBookId));
        //then
        assertThat(throwable).isInstanceOf(NotFoundWordBookException.class);
    }
}