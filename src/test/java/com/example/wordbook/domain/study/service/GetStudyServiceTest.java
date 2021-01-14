package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.study.StudyGroupRule;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DomainFactory.class})
@ExtendWith(MockitoExtension.class)
class GetStudyServiceTest {

    @MockBean
    private StudyRepository studyRepository;

    @Autowired
    private DomainFactory domainFactory;

    @Test
    @DisplayName("정상적으로 스터디를 받아오는 테스트")
    void getEntityByFindByUserIdAndStudyGroupsId() {
        //given
        GetStudyService getStudyService = new GetStudyService(studyRepository);

        long userId = 1L;
        long studyGroupId = 2L;
        long studyId = 0L;

        Study mockStudy = Study.builder()
                .id(studyId)
                .user(domainFactory.createUser(userId))
                .studyGroup(domainFactory.createStudyGroup(studyGroupId))
                .studyGroupRule(StudyGroupRule.NORMAL)
                .build();

        given(studyRepository.findByUserIdAndStudyGroupId(anyLong(), anyLong())).willReturn(Optional.of(mockStudy));

        //when
        Study study = getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId);

        //then
        assertThat(study).isNotNull();
    }

    @Test
    @DisplayName("유효하지 않은 스터디를 받아올때 에러 반환 테스트")
    void getEntityByFindByUserIdAndStudyGroupsId_NotFound_Test() {
        //given
        GetStudyService getStudyService = new GetStudyService(studyRepository);

        long userId = 1L;
        long studyGroupId = 2L;

        given(studyRepository.findByUserIdAndStudyGroupId(anyLong(), anyLong())).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> getStudyService.getEntityByFindByUserIdAndStudyGroupsId(userId, studyGroupId));

        //then
        assertThat(throwable).isInstanceOf(NotFoundStudyException.class);
    }
}