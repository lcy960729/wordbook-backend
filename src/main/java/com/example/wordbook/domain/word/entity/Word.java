package com.example.wordbook.domain.word.entity;

import com.example.wordbook.domain.wordbook.entity.WordBook;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "word")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "isUsing", nullable = false)
    private Boolean isUsing;

    @ManyToOne
    @JoinColumn(name = "wordbook_id")
    private WordBook wordBook;

    @NotBlank
    @Column(name = "voca", unique = true, nullable = false)
    private String voca;

    @NotBlank
    @Column(name = "mean", nullable = false)
    private String meaning;

    @Builder
    public Word(Long id, Boolean isUsing, WordBook wordBook, String voca, String meaning) {
        this.id = id;
        this.isUsing = isUsing;
        this.wordBook = wordBook;
        this.voca = voca;
        this.meaning = meaning;
    }

    public void use() {
        this.isUsing = true;
    }

    public void disuse() {
        this.isUsing = false;
    }

    public void setWordBook(WordBook wordBook) {
        this.wordBook = wordBook;
    }
}
