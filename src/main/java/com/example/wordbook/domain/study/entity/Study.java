package com.example.wordbook.domain.study.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;

    @Builder
    public Study(Long id, User user, StudyGroup studyGroup) {
        this.id = id;
        this.user = user;
        this.studyGroup = studyGroup;
    }
}
