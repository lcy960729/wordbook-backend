package com.example.wordbook.domain.user.dto.response;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
import com.example.wordbook.global.component.LinkFactory;
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
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class StudyGroupDTO extends RepresentationModel<StudyGroupDTO> {
        private Long id;
        private String name;

        @Builder
        public StudyGroupDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Builder
    public UserDetailResponseDTO(Long id, String name, String email, List<WordBookDTO> wordBookDTOList, List<StudyGroupDTO> studyGroupList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wordBookDTOList = wordBookDTOList;
        this.studyGroupList = studyGroupList;
    }

    public void makeLinks() throws Exception {
        makeLinksUserDetailResponseDTO();
        makeLinksAtWordBookDTOList(id);
        makeLinksAtStudyGroupDTOList(id);
    }

    private void makeLinksUserDetailResponseDTO() throws Exception {
        add(LinkFactory.User.self(id));
        add(LinkFactory.User.update(id));
//        add(selfLinkBuilder.withRel("delete_user"));

        add(LinkFactory.StudyGroup.create(id));
        add(LinkFactory.UserWordBook.create(id));
    }

    private void makeLinksAtWordBookDTOList(Long userId) throws Exception {
        for (UserDetailResponseDTO.WordBookDTO wordBookDTO : wordBookDTOList) {
            wordBookDTO.add(LinkFactory.UserWordBook.get(userId, wordBookDTO.getId()));
        }
    }

    private void makeLinksAtStudyGroupDTOList(Long userId) throws Exception {
        for (UserDetailResponseDTO.StudyGroupDTO studyGroupDTO : studyGroupList) {
            studyGroupDTO.add(LinkFactory.StudyGroup.get(userId, studyGroupDTO.getId()));
        }
    }
}
