package com.example.wordbook.domain.word.dto.response;

import com.example.wordbook.global.enums.DomainLink;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WordDetailDTO extends RepresentationModel<WordDetailDTO> {
    private Long id;
    private String voca;
    private String meaning;

    @Builder
    public WordDetailDTO(Long id, String voca, String meaning) {
        this.id = id;
        this.voca = voca;
        this.meaning = meaning;
    }

    public void makeLinks(Long userId, Long wordBookId, Long wordId) {
        add(DomainLink.WordOfUserWordBook.self(userId, wordBookId, wordId));

        add(DomainLink.WordOfUserWordBook.update(userId, wordBookId, wordId));
        add(DomainLink.WordOfUserWordBook.delete(userId, wordBookId, wordId));

        add(DomainLink.UserWordBook.get(userId, wordBookId));
    }

    public void makeLinks(Long userId, Long studyGroupId, Long wordBookId, Long wordId) {
        add(DomainLink.WordOfStudyGroupWordBook.self(userId, studyGroupId, wordBookId, wordId));

        add(DomainLink.WordOfStudyGroupWordBook.update(userId, studyGroupId, wordBookId, wordId));
        add(DomainLink.WordOfStudyGroupWordBook.delete(userId, studyGroupId, wordBookId, wordId));

        add(DomainLink.StudyGroupWordBook.get(userId, studyGroupId, wordBookId));
    }
}
