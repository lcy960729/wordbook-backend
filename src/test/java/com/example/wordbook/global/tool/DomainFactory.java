package com.example.wordbook.global.tool;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.global.enums.StudyGroupRole;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class DomainFactory {

    private final User userOfStudyGroupAdmin;
    private final User userOfStudyGroupNormal;
    private final Study studyOfStudyGroupAdmin;
    private final Study studyOfStudyGroupNormal;
    private final StudyGroup studyGroup;
    private final StudyGroupWordBook studyGroupWordBook;
    private final UserWordBook userWordBookOfAdminUser;
    private final UserWordBook userWordBook;

    public DomainFactory() {
        userOfStudyGroupAdmin = createUser(0L);
        userOfStudyGroupNormal = createUser(1L);

        studyGroup = createStudyGroup(0L);

        studyOfStudyGroupAdmin = createStudyOfNormalUser(0L, studyGroup, userOfStudyGroupNormal);
        userOfStudyGroupAdmin.joinToStudy(studyOfStudyGroupAdmin);
        studyGroup.addStudy(studyOfStudyGroupAdmin);

        studyOfStudyGroupNormal = createStudyOfAdminUser(1L, studyGroup, userOfStudyGroupNormal);
        userOfStudyGroupNormal.joinToStudy(studyOfStudyGroupNormal);
        studyGroup.addStudy(studyOfStudyGroupNormal);


        studyGroupWordBook = createStudyGroupWordBook(0L, studyGroup);
        studyGroup.addWordBook(studyGroupWordBook);

        userWordBookOfAdminUser = createUserWordBook(0L, userOfStudyGroupAdmin);
        userOfStudyGroupAdmin.addWordBook(userWordBookOfAdminUser);

        userWordBook = createUserWordBook(1L, userOfStudyGroupNormal);
        userOfStudyGroupNormal.addWordBook(userWordBook);

        int length = 4;
        LongStream.range(0, length).forEach(i -> {
            studyGroupWordBook.addWord(createWord(i));

            userWordBookOfAdminUser.addWord(createWord(i));
            userWordBook.addWord(createWord(i));
        });
    }

    public Study getStudyOfStudyGroupAdmin() {
        return studyOfStudyGroupAdmin;
    }

    public Study getStudyOfStudyGroupNormal() {
        return studyOfStudyGroupNormal;
    }

    public User getUserOfStudyGroupAdmin() {
        return userOfStudyGroupAdmin;
    }

    public User getUserOfStudyGroupNormal() {
        return userOfStudyGroupNormal;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public StudyGroupWordBook getStudyGroupWordBook() {
        return studyGroupWordBook;
    }

    public UserWordBook getUserWordBookOfAdminUser() {
        return userWordBookOfAdminUser;
    }

    public UserWordBook getUserWordBook() {
        return userWordBook;
    }

    private User createUser(long id) {
        return User.builder()
                .id(id)
                .name("testUserName")
                .pw("testPw")
                .email("testEmail@test.com")
                .build();
    }

    private StudyGroupWordBook createStudyGroupWordBook(Long id, StudyGroup studyGroup) {
        return StudyGroupWordBook.builder()
                .id(id)
                .isUsing(true)
                .studyGroup(studyGroup)
                .name("testStudyGroupWordBook")
                .build();
    }

    private UserWordBook createUserWordBook(Long id, User user) {
        return UserWordBook.builder()
                .id(id)
                .user(user)
                .isUsing(true)
                .name("testUserWordBook")
                .build();
    }

    private StudyGroup createStudyGroup(Long id) {
        return StudyGroup.builder()
                .id(id)
                .name("testUserName")
                .isUsing(true)
                .build();
    }

    private Study createStudyOfAdminUser(Long studyId, StudyGroup studyGroup, User user) {
        return Study.builder()
                .id(studyId)
                .isUsing(true)
                .studyGroupRole(StudyGroupRole.ADMIN)
                .user(user)
                .studyGroup(studyGroup)
                .build();
    }


    private Study createStudyOfNormalUser(Long studyId, StudyGroup studyGroup, User user) {
        return Study.builder()
                .id(studyId)
                .isUsing(true)
                .studyGroupRole(StudyGroupRole.NORMAL)
                .user(user)
                .studyGroup(studyGroup)
                .build();
    }

    private Word createWord(Long id) {
        return Word.builder()
                .id(id)
                .isUsing(true)
                .voca("test-" + id)
                .meaning("testMeaning-" + id)
                .build();
    }
}
