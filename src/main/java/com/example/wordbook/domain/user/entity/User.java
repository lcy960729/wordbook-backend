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
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pw", nullable = false)
    private String pw;

    @OneToMany(mappedBy = "user")
    private final List<UserWordBook> userWordBookList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Study> studyList = new ArrayList<>();
    
    @Builder
    public User(Long id, String name, String email, String pw, List<UserWordBook> userWordBookList, List<Study> studyList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;

        if (userWordBookList != null)
            this.userWordBookList.addAll(userWordBookList);

        if (studyList != null)
            this.studyList.addAll(studyList);
    }

    public void joinToStudy(Study study) {
        this.studyList.add(study);
    }
    public void signOutToStudy(Study study) {
        this.studyList.remove(study);}
}
