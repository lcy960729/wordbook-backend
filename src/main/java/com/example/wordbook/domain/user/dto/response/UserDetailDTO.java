package com.example.wordbook.domain.user.dto.response;

import com.example.wordbook.global.enums.DomainLink;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDetailDTO extends RepresentationModel<UserDetailDTO> {
    private Long id;
    private String name;
    private String email;

    private List<WordBookDTO> wordBookDTOList;
    private List<StudyGroupDTO> studyGroupDTOList;

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
    public UserDetailDTO(Long id, String name, String email, List<WordBookDTO> wordBookDTOList, List<StudyGroupDTO> studyGroupDTOList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wordBookDTOList = wordBookDTOList;
        this.studyGroupDTOList = studyGroupDTOList;
    }

    public void makeLinks() {
        makeLinksUserDetailResponseDTO();
        makeLinksAtWordBookDTOList(id);
        makeLinksAtStudyGroupDTOList(id);
    }

    private void makeLinksUserDetailResponseDTO() {
        add(DomainLink.User.self(id));
        add(DomainLink.User.update(id));
        add(DomainLink.User.delete(id));

        add(DomainLink.StudyGroup.create(id));
        add(DomainLink.UserWordBook.create(id));
    }

    private void makeLinksAtWordBookDTOList(Long userId) {
        for (UserDetailDTO.WordBookDTO wordBookDTO : wordBookDTOList) {
            wordBookDTO.add(DomainLink.UserWordBook.get(userId, wordBookDTO.getId()));
        }
    }

    private void makeLinksAtStudyGroupDTOList(Long userId) {
        for (UserDetailDTO.StudyGroupDTO studyGroupDTO : studyGroupDTOList) {
            studyGroupDTO.add(DomainLink.StudyGroup.get(userId, studyGroupDTO.getId()));
        }
    }
}
