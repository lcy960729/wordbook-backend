package com.example.wordbook.domain.group.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    //@OneToMany(mappedBy = "wordBook")
    //private List<Long> userList;

//    @OneToMany(mappedBy = "wordBook")
//    private List<GroupWordBook> wordBookList;
}
