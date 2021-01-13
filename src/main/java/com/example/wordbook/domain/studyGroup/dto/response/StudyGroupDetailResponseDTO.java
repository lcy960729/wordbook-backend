package com.example.wordbook.domain.studyGroup.dto.response;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;

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
    public StudyGroupDetailResponseDTO(Long userId, Long id, String name, List<UserDTO> userList, List<WordBookDTO> wordBookList) throws Exception {
        this.id = id;
        this.name = name;
        this.userList = userList;
        this.wordBookList = wordBookList;

        WebMvcLinkBuilder selfLinkBuilder = linkTo(methodOn(StudyGroupController.class).create(userId.toString(), null)).slash(this.id);
        add(selfLinkBuilder.withSelfRel());

        //TODO 그룹 권한 에 따라 달라져야함
        add(selfLinkBuilder.withRel("update_studyGroup"));
        add(selfLinkBuilder.withRel("delete_studyGroup"));

        add(linkTo(UserController.class).slash(userId).withRel("pre"));
    }
}

