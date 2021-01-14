package com.example.wordbook.domain.study.entity;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity(name = "study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "isUsing", nullable = false)
    private Boolean isUsing;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private StudyGroup studyGroup;

    @Column(name = "role", nullable = false)
    private StudyGroupRole studyGroupRole;

    public void use(){
        isUsing = true;
    }

    @Builder
    public Study(Long id, Boolean isUsing, User user, StudyGroup studyGroup, StudyGroupRole studyGroupRole) {
        this.id = id;
        this.isUsing = isUsing;
        this.user = user;
        this.studyGroup = studyGroup;
        this.studyGroupRole = studyGroupRole;
    }
}
