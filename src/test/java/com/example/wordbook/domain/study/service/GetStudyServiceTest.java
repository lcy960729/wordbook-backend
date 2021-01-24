package com.example.wordbook.domain.study.service;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.repository.StudyRepository;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.server.UnicastServerRef;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class GetStudyServiceTest extends StudyServiceTest{

    @Mock
    private StudyRepository studyRepository;

    @Test
    @DisplayName("정상적으로 스터디를 받아오는 테스트")
    void getEntityByFindByUserIdAndStudyGroupsId() {
        //given
        GetStudyService getStudyService = new GetStudyService(studyRepository);

        Study tempStudy = domainFactory.getStudyOfStudyGroupNormal();
        long userId = tempStudy.getUser().getId();
        long studyGroupId = tempStudy.getStudyGroup().getId();

        given(studyRepository.findByUserIdAndStudyGroupId(anyLong(), anyLong())).willReturn(Optional.of(tempStudy));

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