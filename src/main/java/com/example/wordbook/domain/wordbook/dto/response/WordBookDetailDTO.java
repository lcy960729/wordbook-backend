package com.example.wordbook.domain.wordbook.dto.response;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        WebMvcLinkBuilder selfLinkBuilder = linkTo(methodOn(StudyGroupWordBookController.class).get(userId.toString(), studyGroupId.toString(), id.toString()));
        add(selfLinkBuilder.withSelfRel());
        add(linkTo(methodOn(StudyGroupController.class).get(userId.toString(), studyGroupId.toString())).withRel("pre"));
    }

    public void makeLinks(Long userId) throws Exception {
        WebMvcLinkBuilder selfLinkBuilder = linkTo(methodOn(UserWordBookController.class).get(userId.toString(), id.toString()));
        add(selfLinkBuilder.withSelfRel());
        add(linkTo(methodOn(UserController.class).get(userId)).withRel("pre"));
    }
}
