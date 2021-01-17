package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.exception.NotFoundStudyGroupException;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DomainFactory.class})
@ExtendWith(MockitoExtension.class)
public class GetStudyGroupServiceTest {

    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Mock
    private StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper;

    @Mock
    private GetStudyService getStudyService;

    @Autowired
    private DomainFactory domainFactory;

    @Test
    @DisplayName("정상적으로 스터디 그룹을 반환하는 테스트")
    void getEntityByIdTest() {
        //given
        GetStudyGroupService getStudyGroupService = new GetStudyGroupService(getStudyService, studyToStudyGroupDetailDtoMapper);

        long studyGroupId = 0L;

        StudyGroup studyGroup = domainFactory.createStudyGroup(studyGroupId);

        given(studyGroupRepository.findById(anyLong())).willReturn(Optional.of(studyGroup));

        //when
        studyGroup = getStudyGroupService.getEntityById(studyGroupId);

        assertThat(studyGroup).isNotNull();
    }

    @Test
    @DisplayName("요청한 스터디 그룹이 유효하지 않을때 에러를 반환하는 테스")
    void getEntityById_isNull_ErrorTest() {
        //given
        GetStudyGroupService getStudyGroupService = new GetStudyGroupService(getStudyService, studyToStudyGroupDetailDtoMapper);

        long studyGroupId = 0L;

        given(studyGroupRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> getStudyGroupService.getEntityById(studyGroupId));

        assertThat(throwable).isInstanceOf(NotFoundStudyGroupException.class);
    }
}
