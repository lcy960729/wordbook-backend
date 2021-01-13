package com.example.wordbook.domain.user.dto.response;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.wordbook.controller.WordBookController;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
public class UserDetailResponseDTO extends RepresentationModel<UserDetailResponseDTO> {
    private Long id;
    private String name;
    private String email;
    private List<WordBookDTO> wordBookDTOList;
    private List<StudyGroupDTO> studyGroupList;

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class WordBookDTO extends RepresentationModel<WordBookDTO> {
        private Long id;
        private String name;

        @Builder
        public WordBookDTO(Long id, String name) {
            this.id = id;
            this.name = name;

            add(linkTo(WordBookController.class).withRel("get_wordBook"));
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class StudyGroupDTO extends RepresentationModel<StudyGroupDTO> {
        private Long id;
        private String name;

        @Builder
        public StudyGroupDTO(Long userId, Long id, String name) throws Exception {
            this.id = id;
            this.name = name;

            add(linkTo(methodOn(StudyGroupController.class).get(userId, this.id)).withRel("get_studyGroup"));
        }
    }

    @Builder
    public UserDetailResponseDTO(Long id, String name, String email, List<WordBookDTO> wordBookDTOList, List<StudyGroupDTO> studyGroupList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wordBookDTOList = wordBookDTOList;
        this.studyGroupList = studyGroupList;

        WebMvcLinkBuilder selfLinkBuilder = linkTo(UserController.class).slash(this.id);
        add(selfLinkBuilder.withSelfRel());
        add(selfLinkBuilder.withRel("update_user"));
        add(selfLinkBuilder.withRel("delete_user"));
    }
}
