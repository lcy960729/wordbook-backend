package com.example.wordbook.domain.user.entity;

import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "user")
    private List<UserWordBook> userWordBookList;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name ="pw", nullable = false)
    private String pw;

//    @OneToMany(mappedBy = "wordBook")
//    private List<GroupWordBook> wordBookList;

    @Builder
    public User(Long id, String name, String userId, String pw) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.pw = pw;
    }
}
