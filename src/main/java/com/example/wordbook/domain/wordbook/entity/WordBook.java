package com.example.wordbook.domain.wordbook.entity;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.exception.NotFoundWordException;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "wordBook", cascade = CascadeType.REMOVE)
    private final List<Word> words = new ArrayList<>();

    public void use() {
        this.isUsing = true;
    }

    public void disuse() {
        this.isUsing = false;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public Word getWordById(long wordId) {
        return words.stream().filter(word -> word.getId() == wordId)
                .findFirst()
                .orElseThrow(NotFoundWordException::new);
    }

    public void deleteWord(long wordId) {
        Word word = words.stream().filter(w -> w.getId() == wordId)
            .findFirst()
            .orElseThrow(NotFoundWordException::new);

        word.disuse();
        words.remove(word);
    }
}
