package com.example.wordbook.domain.wordbook.entity;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.word.entity.Word;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "studyGroupWordBook")
@DiscriminatorValue("studyGroup")
@PrimaryKeyJoinColumn(name = "studyGroupWordBook_id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class StudyGroupWordBook extends WordBook {
    @ManyToOne
    @JoinColumn(name = "studygroup_id", nullable = false)
    private StudyGroup studyGroup;

    @Builder
    public StudyGroupWordBook(Long id, Boolean isUsing, String name, List<Word> words, StudyGroup studyGroup) {
        super( id, isUsing, name);

        if (words != null){
            getWords().addAll(words);
        }

        this.studyGroup = studyGroup;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setIsUsing(Boolean isUsing) {
        super.setIsUsing(isUsing);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
