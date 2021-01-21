package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.CreateStudyService;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.request.UpdateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class UpdateStudyGroupServiceTest extends StudyGroupServiceTest {

    @MockBean
    private StudyGroupRepository studyGroupRepository;

    @MockBean
    private GetStudyService getStudyService;

    private UpdateStudyGroupService updateStudyGroupService;

    @BeforeEach
    void setUp() {
        updateStudyGroupService =
                new UpdateStudyGroupService(
                        studyGroupRepository,
                        getStudyService,
                        studyToStudyGroupDetailDtoMapper);
    }

    @Test
    @DisplayName("정상적으로 그룹을 수정하는 테스트")
    void update() {
        //given
        UpdateStudyGroupDTO updateStudyGroupDTO = new UpdateStudyGroupDTO("updateName");

        long adminId = 0;
        long studyGroupId = 10;

        StudyGroup studyGroup = DomainFactory.createStudyGroup(studyGroupId);
        studyGroup.setName(updateStudyGroupDTO.getName());

        User user = DomainFactory.createUser(adminId);

        Study study = DomainFactory.createStudyOfAdminUser(0L, studyGroup, user);

        given(studyGroupRepository.save(any(StudyGroup.class))).willReturn(studyGroup);
        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        //when
        StudyGroupDetailDTO studyGroupDetailDTO = updateStudyGroupService.update(adminId, studyGroupId, updateStudyGroupDTO);

        //then
        assertThat(studyGroupDetailDTO).isNotNull();
        assertThat(studyGroupDetailDTO.getId()).isEqualTo(studyGroup.getId());
        assertThat(studyGroupDetailDTO.getName()).isEqualTo(studyGroup.getName());
    }

    @Test
    @DisplayName("관리자 권한이 없는 유저가 그룹을 수정할때 에러를 반환하는 테스트")
    void update_HasNotPermissionUser_ErrorTest() {
        //given
        UpdateStudyGroupDTO updateStudyGroupDTO = new UpdateStudyGroupDTO("updateName");

        long adminId = 0;
        long studyGroupId = 10;

        StudyGroup studyGroup = DomainFactory.createStudyGroup(studyGroupId);
        studyGroup.setName(updateStudyGroupDTO.getName());

        given(studyGroupRepository.save(any(StudyGroup.class))).willReturn(studyGroup);

        BusinessException businessException = new BusinessException(ErrorCode.HAS_NOT_ADMIN_PERMISSION_TO_ACCESS_STUDYGROUP_WORDBOOK);
        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willThrow(businessException);

        //when
        Throwable throwable = catchThrowable(()-> updateStudyGroupService.update(adminId, studyGroupId, updateStudyGroupDTO));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class);
        assertThat(((BusinessException) throwable).getErrorCode()).isEqualTo(ErrorCode.HAS_NOT_ADMIN_PERMISSION_TO_ACCESS_STUDYGROUP_WORDBOOK);
    }
}