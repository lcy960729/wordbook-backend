package com.example.wordbook.domain.user.entity;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name ="pw", nullable = false)
    private String pw;

    @OneToMany(mappedBy = "user")
    private List<UserWordBook> userWordBookList;

    @OneToMany(mappedBy = "user")
    private List<Study> studyList;

    @Builder
    public User(Long id, String name, String email, String pw) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public void participateInStudyGroup(Study study){
        if (studyList == null)
            studyList = new ArrayList<>();

        this.studyList.add(study);
    }
}
