package com.example.wordbook.domain.studyGroup.dto.response;

import com.example.wordbook.domain.study.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;

import com.example.wordbook.global.component.LinkFactory;
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
public class StudyGroupDetailResponseDTO extends RepresentationModel<StudyGroupDetailResponseDTO> {
    private Long id;
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
    public StudyGroupDetailResponseDTO(Long id, String name, List<UserDTO> userDTOList, List<WordBookDTO> wordBookDTOList) {
        this.id = id;
        this.name = name;
        this.userDTOList = userDTOList;
        this.wordBookDTOList = wordBookDTOList;
    }

    public void makeLinks(Study study) throws Exception {
        Long userId = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();

        makeLinksAtWordBookDTOList(userId, studyGroupId);
        makeLinksAtUserDTOList(userId, studyGroupId);
        makeLinksStudyGroupDetailResponseDTO(study.getStudyGroupRole(), userId, studyGroupId);
    }

    private void makeLinksStudyGroupDetailResponseDTO(StudyGroupRole studyGroupRole, Long userId, Long studyGroupId) throws Exception {
        add(LinkFactory.StudyGroup.self(userId, studyGroupId));

        if (studyGroupRole == StudyGroupRole.ADMIN) {
            add(LinkFactory.StudyGroup.update(userId, studyGroupId));
            //add(LinkFactory.StudyGroup.delete(study.getUser().getId(),study.getStudyGroup().getId()));

            add(LinkFactory.StudyGroupWordBook.create(userId, studyGroupId));
        }

        add(LinkFactory.User.get(userId));
    }

    private void makeLinksAtWordBookDTOList(Long userId, Long studyGroupId) throws Exception {
        for (WordBookDTO wordBookDTO : wordBookDTOList) {
            wordBookDTO.add(LinkFactory.StudyGroupWordBook.get(userId, studyGroupId, id));
        }
    }

    private void makeLinksAtUserDTOList(Long userId, Long studyGroupId) throws Exception {
        for (UserDTO userDTO : userDTOList) {
            //userDTO.add(LinkFactory.StudyGroupWordBook.get(userId, studyGroupId, id));
        }
    }
}

