package com.example.wordbook.domain.groupwordbook.entity;

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
    public GroupWordBook(Boolean isUsing,  String name, List<Word> words, Long groupId) {
        super( isUsing, name, words);
        this.groupId = groupId;
    }

    public void changeName( String name){
        super.changeName(name);
    }

    @Override
    public void setUsing(Boolean isUsing) {
        super.setUsing(isUsing);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
