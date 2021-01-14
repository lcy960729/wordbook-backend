package com.example.wordbook.domain.studyGroup.entity;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "studygroup")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "isUsing", nullable = false)
    private Boolean isUsing;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "studyGroup")
    private final List<StudyGroupWordBook> studyGroupWordBookList = new ArrayList<>();

    @OneToMany(mappedBy = "studyGroup")
    private final List<Study> studyList = new ArrayList<>();

    public void use() {
        this.isUsing = true;
    }

    public void disuse() {
        this.isUsing = false;
    }

    public void addStudy(Study study) {
        this.studyList.add(study);
    }

    public void addWordBook(StudyGroupWordBook studyGroupWordBook) {
        this.studyGroupWordBookList.add(studyGroupWordBook);
    }

    @Builder
    public StudyGroup(Long id, Boolean isUsing, String name, List<StudyGroupWordBook> studyGroupWordBookList, List<Study> studyList) {
        this.id = id;
        this.isUsing = isUsing;
        this.name = name;

        if (studyGroupWordBookList != null)
        this.studyGroupWordBookList.addAll(studyGroupWordBookList);

        if (studyList != null)
        this.studyList.addAll(studyList);
    }
}
