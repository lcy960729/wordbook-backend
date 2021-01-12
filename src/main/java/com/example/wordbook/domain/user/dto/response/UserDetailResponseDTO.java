package com.example.wordbook.domain.user.dto.response;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.wordbook.controller.WordBookController;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDetailResponseDTO extends RepresentationModel<UserDetailResponseDTO> {
    private Long id;
    private String name;
    private String email;
    private List<UserWordBookDTO> userWordBookList;
    private List<StudyGroupDTO> studyGroupList;

    @NoArgsConstructor
    @Data
    public static class UserWordBookDTO {
        private Long id;
        private String name;

        @Builder
        public UserWordBookDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @NoArgsConstructor
    @Data
    public static class StudyGroupDTO {
        private Long id;
        private String name;

        @Builder
        public StudyGroupDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Builder
    public UserDetailResponseDTO(Long id, String name, String email, List<UserWordBookDTO> userWordBookList, List<StudyGroupDTO> studyGroupList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userWordBookList = userWordBookList;
        this.studyGroupList = studyGroupList;

        WebMvcLinkBuilder selfLinkBuilder = linkTo(UserController.class).slash(this.id);
        add(selfLinkBuilder.withSelfRel());
        add(selfLinkBuilder.withRel("update_user"));
        add(selfLinkBuilder.withRel("delete_user"));
        add(linkTo(StudyGroupController.class).withRel("get_studyGroup"));
        add(linkTo(WordBookController.class).withRel("get_wordBook"));
    }
}
