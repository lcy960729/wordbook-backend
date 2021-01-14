package com.example.wordbook.domain.studyGroup.dto.response;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;

import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
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
public class StudyGroupDetailResponseDTO extends RepresentationModel<StudyGroupDetailResponseDTO> {
    private Long id;
    private String name;
    private List<UserDTO> userList;
    private List<WordBookDTO> wordBookList;

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class UserDTO extends RepresentationModel<UserDTO> {
        private Long id;
        private String name;

        @Builder
        public UserDTO(Long id, String name) {
            this.id = id;
            this.name = name;

//            add(selfLinkBuilder.slash("users").withRel("get_userInGroups"));
        }
    }

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

            //add(selfLinkBuilder.slash("wordbooks").withRel("get_wordBooksInGroups"));
        }
    }

    @Builder
    public StudyGroupDetailResponseDTO(Long id, String name, List<UserDTO> userList, List<WordBookDTO> wordBookList) {
        this.id = id;
        this.name = name;
        this.userList = userList;
        this.wordBookList = wordBookList;
    }

    public void makeLinks(Study study) throws Exception {
        WebMvcLinkBuilder selfLinkBuilder = linkTo(methodOn(StudyGroupController.class).get(
                study.getUser().getId().toString(),
                study.getStudyGroup().getId().toString()));

        add(selfLinkBuilder.withSelfRel());

        if (study.getStudyGroupRole() == StudyGroupRole.ADMIN) {
            makeLinksAdminRole(study, selfLinkBuilder);
        }

        add(linkTo(methodOn(UserController.class).get(study.getUser().getId())).withRel("pre"));
    }

    private void makeLinksAdminRole(Study study, WebMvcLinkBuilder selfLinkBuilder) throws Exception {
        add(selfLinkBuilder.withRel("update_studyGroup"));
        add(selfLinkBuilder.withRel("delete_studyGroup"));

        add(linkTo(methodOn(StudyGroupWordBookController.class).create(
                study.getUser().getId().toString(),
                study.getStudyGroup().getId().toString(),
                null)).withRel("create_studyGroupWordBook"));
    }
}

