package com.example.wordbook.domain.study.repository;

import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.studyGroup.repository.StudyGroupRepository;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    private User getUser() {
        return userRepository.save(User.builder()
                .name("testName")
                .pw("testPw")
                .email("testEmail")
                .build());
    }

    private StudyGroup getStudyGroup() {
        return studyGroupRepository.save(StudyGroup.builder()
                .name("testGroupName")
                .isUsing(true)
                .build());
    }

    @Test
    void findByUserIdAndStudyGroupId() {
        // given
        User user = getUser();
        StudyGroup studyGroup = getStudyGroup();

        Study study = Study.builder()
                .user(user)
                .studyGroup(studyGroup)
                .studyGroupRole(StudyGroupRole.ADMIN)
                .build();

        study = studyRepository.save(study);

        user.joinToStudy(study);
        studyGroup.addStudy(study);

        //when
        Optional<Study> optionalStudy = studyRepository.findByUserIdAndStudyGroupId(user.getId(), studyGroup.getId());
        Study studyToFindByUserIdAndStudyGroupId = optionalStudy.orElse(null);

        assertThat(studyToFindByUserIdAndStudyGroupId).isNotNull();
        assertThat(studyToFindByUserIdAndStudyGroupId.getId()).isEqualTo(study.getId());
    }
}