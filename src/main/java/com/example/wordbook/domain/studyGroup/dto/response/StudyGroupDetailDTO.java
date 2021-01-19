package com.example.wordbook.domain.studyGroup.dto.response;

import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;

import com.example.wordbook.global.enums.DomainLink;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class StudyGroupDetailDTO extends RepresentationModel<StudyGroupDetailDTO> {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private List<UserDTO> userDTOList;

    private List<WordBookDTO> wordBookDTOList;

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
        }
    }

    @Builder
    public StudyGroupDetailDTO(Long id, String name, List<UserDTO> userDTOList, List<WordBookDTO> wordBookDTOList) {
        this.id = id;
        this.name = name;
        this.userDTOList = userDTOList;
        this.wordBookDTOList = wordBookDTOList;
    }

    public void makeLinks(Long userId, Long studyGroupId, StudyGroupRole studyGroupRole) {
        makeLinksAtWordBookDTOList(userId, studyGroupId);
        makeLinksAtUserDTOList(userId, studyGroupId);
        makeLinksStudyGroupDetailResponseDTO(studyGroupRole, userId, studyGroupId);
    }

    private void makeLinksStudyGroupDetailResponseDTO(StudyGroupRole studyGroupRole, Long userId, Long studyGroupId) {
        add(DomainLink.StudyGroup.self(userId, studyGroupId));

        if (studyGroupRole == StudyGroupRole.ADMIN) {
            add(DomainLink.StudyGroup.update(userId, studyGroupId));
            add(DomainLink.StudyGroup.delete(userId, studyGroupId));

            add(DomainLink.StudyGroupWordBook.create(userId, studyGroupId));
        }

        add(DomainLink.User.get(userId));
    }

    private void makeLinksAtWordBookDTOList(Long userId, Long studyGroupId) {
        if (wordBookDTOList == null)
            return;

        for (WordBookDTO wordBookDTO : wordBookDTOList) {
            wordBookDTO.add(DomainLink.StudyGroupWordBook.get(userId, studyGroupId, id));
        }
    }

    private void makeLinksAtUserDTOList(Long userId, Long studyGroupId) {
        if (userDTOList == null)
            return;

        for (UserDTO userDTO : userDTOList) {
            //userDTO.add(LinkType.StudyGroupWordBook.get(userId, studyGroupId, id));
        }
    }
}

