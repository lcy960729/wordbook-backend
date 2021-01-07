package com.example.wordbook.domain.userwordbook.entity;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "userWordBook")
@DiscriminatorValue("user")
@PrimaryKeyJoinColumn(name = "userWordBook_id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class UserWordBook extends WordBook {
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public UserWordBook(Boolean isUsing, String name, List<Word> words, User userId) {
        super(isUsing, name, words);
        this.user = userId;
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
