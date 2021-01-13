package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.exception.NotFoundStudyGroupException;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.global.mapper.StudyGroupMapper;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class GetStudyGroupServiceTest {

    @MockBean
    private StudyGroupRepository studyGroupRepository;
    @MockBean
    private StudyGroupMapper studyGroupMapper;

    @Test
    @DisplayName("정상적으로 스터디 그룹을 반환하는 테스트")
    void getEntityByIdTest() {
        //given
        GetStudyGroupService getStudyGroupService = new GetStudyGroupService(studyGroupRepository, studyGroupMapper);

        long studyGroupId = 0L;
        StudyGroup studyGroup = StudyGroup.builder()
                .id(studyGroupId)
                .isUsing(true)
                .name("testName")
                .build();

        given(studyGroupRepository.findById(studyGroupId)).willReturn(Optional.of(studyGroup));

        //when
        studyGroup = getStudyGroupService.getEntityById(studyGroupId);

        assertThat(studyGroup).isNotNull();
    }

    @Test
    @DisplayName("요청한 스터디 그룹이 유효하지 않을때 에러를 반환하는 테스")
    void getEntityById_isNull_ErrorTest() {
        //given
        GetStudyGroupService getStudyGroupService = new GetStudyGroupService(studyGroupRepository, studyGroupMapper);

        long studyGroupId = 0L;

        given(studyGroupRepository.findById(studyGroupId)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> getStudyGroupService.getEntityById(studyGroupId));

        assertThat(throwable).isInstanceOf(NotFoundStudyGroupException.class);
    }
}
