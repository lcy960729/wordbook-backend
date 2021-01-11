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

    @Column(name="isUsing", nullable = false)
    private Boolean isUsing;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "studyGroup")
    private List<StudyGroupWordBook> studyGroupWordBookList;

    @OneToMany(mappedBy = "studyGroup")
    private List<Study> studyList;

    public void use(){
        this.isUsing = true;
    }

    public void disuse(){
        this.isUsing = false;
    }

    public void addStudy(Study study){
        if (studyList == null)
            studyList = new ArrayList<>();

        this.studyList.add(study);
    }

    @Builder
    public StudyGroup(Long id, Boolean isUsing, String name, List<StudyGroupWordBook> studyGroupWordBookList, List<Study> studyList) {
        this.id = id;
        this.isUsing = isUsing;
        this.name = name;
        this.studyGroupWordBookList = studyGroupWordBookList;
        this.studyList = studyList;
    }
}
