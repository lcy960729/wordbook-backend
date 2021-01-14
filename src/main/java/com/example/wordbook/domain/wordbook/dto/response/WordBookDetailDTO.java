package com.example.wordbook.domain.wordbook.dto.response;

import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.global.component.LinkFactory;
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
    private List<Word> words;

    @Builder
    public WordBookDetailDTO(Long id, Boolean isUsing, String name, List<Word> words) {
        this.id = id;
        this.isUsing = isUsing;
        this.name = name;
        this.words = words;
    }

    public void makeLinks(Long userId, Long studyGroupId) throws Exception {
        add(LinkFactory.StudyGroupWordBook.self(userId, studyGroupId, id));
        add(LinkFactory.StudyGroup.get(userId, studyGroupId));
    }

    public void makeLinks(Long userId) throws Exception {
        add(LinkFactory.UserWordBook.self(userId, id));
        add(LinkFactory.User.get(userId));
    }
}
