package com.example.wordbook.domain.word.entity;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import lombok.*;

import javax.persistence.*;

@Entity(name = "word")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "isUsing", nullable = false)
    private Boolean isUsing;

    @OneToOne
    @JoinColumn(name = "wordbook_id")
    private WordBook wordBook;

    @Column(name = "voca", unique = true, nullable = false)
    private String voca;

    @Column(name = "mean", nullable = false)
    private String meaning;

    @Builder
    public Word(Boolean isUsing,  WordBook wordBook,  String voca,  String meaning) {
        this.isUsing = isUsing;
        this.wordBook = wordBook;
        this.voca = voca;
        this.meaning = meaning;
    }

    public void setUsing(Boolean using) {
        isUsing = using;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWordBook(WordBook wordBook) {
        this.wordBook = wordBook;
    }
}
