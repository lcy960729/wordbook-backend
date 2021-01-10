package com.example.wordbook.domain.wordbook.entity;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity(name = "groupWordBook")
@DiscriminatorValue("group")
@PrimaryKeyJoinColumn(name = "groupWordBook_id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class GroupWordBook extends WordBook {
    @Column(name = "groupId")
    private Long groupId;

    @Builder
    public GroupWordBook(Boolean isUsing,  String name, List<Word> words, Long group) {
        super( null, isUsing, name, words);
        this.groupId = group;
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
