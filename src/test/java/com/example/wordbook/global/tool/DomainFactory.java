package com.example.wordbook.global.tool;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import org.springframework.stereotype.Component;

@Component
public class DomainFactory {

    public static User createUser(long id){
        return User.builder()
                .id(id)
                .name("testUserName")
                .pw("testPw")
                .email("testEmail@test.com")
                .build();
    }

    public static StudyGroupWordBook createStudyGroupWordBook(Long id){
        return StudyGroupWordBook.builder()
                .id(id)
                .isUsing(true)
                .name("testStudyGroupWordBook")
                .build();
    }

    public static UserWordBook createUserWordBook(Long id){
        return UserWordBook.builder()
                .id(id)
                .isUsing(true)
                .name("testUserWordBook")
                .build();
    }

    public static StudyGroup createStudyGroup(long id){
        return StudyGroup.builder()
                .id(id)
                .name("testUserName")
                .isUsing(true)
                .build();
    }
}
