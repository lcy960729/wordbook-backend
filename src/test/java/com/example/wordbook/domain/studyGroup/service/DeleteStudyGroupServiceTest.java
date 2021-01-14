package com.example.wordbook.domain.studyGroup.service;

import com.example.wordbook.domain.study.StudyGroupRole;
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

@SpringBootTest(classes = {DomainFactory.class})
@ExtendWith(MockitoExtension.class)
class DeleteStudyGroupServiceTest {

    @Mock
    private GetStudyService getStudyService;

    @Mock
    private DeleteStudyService deleteStudyService;

    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    private DomainFactory domainFactory;

    @Test
    @DisplayName("단어장을 정상적으로 삭제하는 테스트")
    void delete() {
        //given
        DeleteStudyGroupService deleteStudyGroupService = new DeleteStudyGroupService(getStudyService, deleteStudyService, studyGroupRepository);

        long userId = 0L;
        User user = domainFactory.createUser(userId);

        long studyGroupId = 0L;
        StudyGroup studyGroup = domainFactory.createStudyGroup(studyGroupId);

        long studyId = 0L;
        Study study = Study.builder()
                .id(studyId)
                .user(user)
                .studyGroup(studyGroup)
                .studyGroupRole(StudyGroupRole.ADMIN)
                .build();

        given(getStudyService.getEntityByFindByUserIdAndStudyGroupsId(anyLong(),anyLong())).willReturn(study);

        //when
        Throwable throwable =  catchThrowable(()->deleteStudyGroupService.delete(userId, studyGroupId));

        //then
        assertThat(throwable).isNull();;
        assertThat(user.getStudyList()).isEmpty();
    }

    //TODO 삭제시 문제될점 생각해보기
}