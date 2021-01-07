package com.example.wordbook.domain.wordbook.entity;

import com.example.wordbook.domain.word.entity.Word;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "wordBook")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class WordBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "isUsing", nullable = false)
    private Boolean isUsing;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "wordBook")
    private List<Word> words;

    public WordBook(Boolean isUsing, String name, List<Word> words) {
        this.isUsing = isUsing;
        this.name = name;
        this.words = words;
    }

    protected void changeName(@NotBlank String name) {
        this.name = name;
    }

    protected void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
