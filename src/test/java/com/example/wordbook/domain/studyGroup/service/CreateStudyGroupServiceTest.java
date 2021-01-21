package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.CreateStudyService;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class CreateStudyGroupServiceTest extends StudyGroupServiceTest {

    @MockBean
    private StudyGroupRepository studyGroupRepository;

    @MockBean
    private CreateStudyService createStudyService;

    @MockBean
    private GetUserService getUserService;


    private CreateStudyGroupService createStudyGroupService;

    @BeforeEach
    void setUp() {
        createStudyGroupService =
                new CreateStudyGroupService(studyGroupRepository,
                        createDtoToStudyGroupMapper,
                        studyToStudyGroupDetailDtoMapper,
                        getUserService, createStudyService);
    }

    @Test
    @DisplayName("정상적으로 그룹을 생성하는 테스트")
    void create() {
        //given
        CreateStudyGroupDTO createStudyGroupDTO = CreateStudyGroupDTO.builder()
                .name("testName")
                .build();

        long userId = 0;
        long studyGroupId = 10;

        StudyGroup studyGroup = DomainFactory.createStudyGroup(studyGroupId);
        User user = DomainFactory.createUser(userId);

        Study study = DomainFactory.createStudyOfAdminUser(0L, studyGroup, user);

        given(studyGroupRepository.save(any(StudyGroup.class))).willReturn(studyGroup);
        given(getUserService.getEntityById(anyLong())).willReturn(user);
        given(createStudyService.create(any(User.class), any(StudyGroup.class))).willReturn(study);

        //when
        StudyGroupDetailDTO studyGroupDetailDTO = createStudyGroupService.create(userId, createStudyGroupDTO);

        //then
        assertThat(studyGroupDetailDTO).isNotNull();
        assertThat(studyGroupDetailDTO.getId()).isEqualTo(studyGroup.getId());
    }
}