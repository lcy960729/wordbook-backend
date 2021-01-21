package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.exception.NotFoundStudyException;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.exception.NotFoundStudyGroupException;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

public class GetStudyGroupServiceTest extends StudyGroupServiceTest {

    @Mock
    private GetStudyService getStudyService;

    private GetStudyGroupService getStudyGroupService;

    @BeforeEach
    void setUp() {
        getStudyGroupService = new GetStudyGroupService(getStudyService, studyToStudyGroupDetailDtoMapper);
    }

    @Test
    @DisplayName("정상적으로 스터디 그룹을 반환하는 테스트")
    void getEntityByIdTest() {
        //given
        long studyGroupId = 0L;
        StudyGroup studyGroup = DomainFactory.createStudyGroup(studyGroupId);

        long userId = 0L;
        User user = DomainFactory.createUser(userId);

        long studyId = 0L;
        Study study = DomainFactory.createStudyOfNormalUser(studyId, studyGroup, user);

        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        //when
        StudyGroupDetailDTO studyGroupDetailDTO = getStudyGroupService.getDetailDTOByUserIdAndStudyGroupId(userId, studyGroupId);

        //then
        assertThat(studyGroupDetailDTO).isNotNull();
        assertThat(studyGroupDetailDTO.getId()).isEqualTo(studyGroup.getId());
    }

    @Test
    @DisplayName("요청한 스터디 그룹이 유효하지 않을때 에러를 반환하는 테스")
    void getEntityById_isNull_ErrorTest() {
        //given
        long userId = 0L;
        long studyGroupId = 0L;

        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(), anyLong())).willThrow(NotFoundStudyException.class);

        //when
        Throwable throwable = catchThrowable(() -> getStudyGroupService.getDetailDTOByUserIdAndStudyGroupId(userId, studyGroupId));

        assertThat(throwable).isInstanceOf(NotFoundStudyException.class);
    }
}
