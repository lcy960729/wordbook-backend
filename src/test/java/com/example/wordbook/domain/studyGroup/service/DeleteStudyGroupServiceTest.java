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

@SpringBootTest(classes = {StudyGroupRepository.class})
class DeleteStudyGroupServiceTest {

    @Mock
    private GetStudyService getStudyService;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    @Test
    @DisplayName("단어장을 정상적으로 삭제하는 테스트")
    void delete() {
        //given
        DeleteStudyGroupService deleteStudyGroupService = new DeleteStudyGroupService(getStudyService, studyGroupRepository);

        long userId = 0L;
        User user = DomainFactory.createUser(userId);

        long studyGroupId = 0L;
        StudyGroup studyGroup = DomainFactory.createStudyGroup(studyGroupId);

        long studyId = 0L;
        Study study = DomainFactory.createStudyOfAdminUser(studyId, studyGroup, user);

        given(getStudyService.getEntityByFindByAdminIdAndStudyGroupsId(anyLong(), anyLong())).willReturn(study);

        //when
        deleteStudyGroupService.delete(userId, studyGroupId);

        //then
        assertThat(user.getStudyList()).isEmpty();

        //TODO 삭제 테스트 코드를 어떻게 작성할지 생각
    }
}