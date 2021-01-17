package com.example.wordbook.domain.wordbook.dto.response;

import com.example.wordbook.global.enums.DomainLink;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WordBookDetailDTO extends RepresentationModel<WordBookDetailDTO> {
    private Long id;
    private Boolean isUsing;
    private String name;
    private List<WordDTO> wordDTOList;


    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class WordDTO extends RepresentationModel<WordDTO> {
        private Long id;
        private String voca;
        private String meaning;

        public WordDTO(Long id, String voca, String meaning) {
            this.id = id;
            this.voca = voca;
            this.meaning = meaning;
        }
    }

    @Builder
    public WordBookDetailDTO(Long id, Boolean isUsing, String name, List<WordDTO> wordDTOList) {
        this.id = id;
        this.isUsing = isUsing;
        this.name = name;
        this.wordDTOList = wordDTOList;
    }

    public void makeLinks(Long userId, Long studyGroupId, boolean isAdmin) {
        //StudyGroupWordBook
        add(DomainLink.StudyGroupWordBook.self(userId, studyGroupId, id));
        if (isAdmin) {
            add(DomainLink.StudyGroupWordBook.update(userId, studyGroupId, id));
            add(DomainLink.StudyGroupWordBook.delete(userId, studyGroupId, id));
        }

        //Word
        add(DomainLink.WordOfStudyGroupWordBook.add(userId, studyGroupId, id));

        //StudyGroup
        add(DomainLink.StudyGroup.get(userId, studyGroupId));

        makeLinks_StudyGroupWordBookWord(userId, studyGroupId, id);
    }

    public void makeLinks_StudyGroupWordBookWord(Long userId, Long studyGroupId, Long wordBookId) {
        for (WordDTO word : wordDTOList) {
            word.add(DomainLink.WordOfStudyGroupWordBook.update(userId, studyGroupId, wordBookId, word.getId()));
            word.add(DomainLink.WordOfStudyGroupWordBook.delete(userId, studyGroupId, wordBookId, word.getId()));
        }
    }

    public void makeLinks(Long userId) {
        //UserWordBook
        add(DomainLink.UserWordBook.self(userId, id));
        add(DomainLink.UserWordBook.update(userId, id));
        add(DomainLink.UserWordBook.delete(userId, id));

        //Word
        add(DomainLink.WordOfUserWordBook.add(userId, id));

        //pre
        add(DomainLink.User.get(userId));

        makeLinks_userWordBookWord(userId, id);
    }

    public void makeLinks_userWordBookWord(Long userId, Long wordBookId) {
        for (WordDTO word : wordDTOList) {
            word.add(DomainLink.WordOfUserWordBook.update(userId, wordBookId, word.getId()));
            word.add(DomainLink.WordOfUserWordBook.delete(userId, wordBookId, word.getId()));
        }
    }
}
