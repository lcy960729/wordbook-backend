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
}
