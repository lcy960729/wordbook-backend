package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.study.service.DeleteStudyService;
import com.example.wordbook.domain.study.service.GetStudyService;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class DeleteStudyGroupServiceTest extends StudyGroupServiceTest{

    @Mock
    private GetStudyService getStudyService;

    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Test
    @DisplayName("단어장을 정상적으로 삭제하는 테스트")
    void delete() {
        //given
        DeleteStudyGroupService deleteStudyGroupService = new DeleteStudyGroupService(getStudyService, studyGroupRepository);

        Study study = domainFactory.getStudyOfStudyGroupAdmin();

        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        //when
        Long studyGroupId = study.getStudyGroup().getId();
        Long adminId = study.getUser().getId();

        deleteStudyGroupService.delete(adminId, studyGroupId);

        //then
//        assertThat(study.getUser().getStudyList()).isIn().isEmpty();

        //TODO 삭제 테스트 코드를 어떻게 작성할지 생각
    }
}