package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.mapper.StudyToStudyGroupDetailDtoMapper;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.global.tool.DomainFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DomainFactory.class, ObjectMapper.class, StudyToStudyGroupDetailDtoMapperImpl.class})
class StudyToStudyGroupDetailDtoMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateDtoToStudyGroupMapperTest.class);

    @Autowired
    private DomainFactory domainFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper;

    @Test
    @DisplayName("entityToDetailDTO 맵핑이 정상적으로 동작 하는 테스트")
    void entityToDetailDTO() throws Exception {
        //given
        StudyGroup studyGroup = domainFactory.createStudyGroup(0L);

        int length = 3;
        IntStream.range(0, length).forEach((i) -> {
            User user = domainFactory.createUser(i);

            Study study = Study.builder()
                    .id((long) i)
                    .studyGroup(studyGroup)
                    .studyGroupRole(StudyGroupRole.ADMIN)
                    .user(user)
                    .build();

            studyGroup.addStudy(study);
            studyGroup.getStudyGroupWordBookList().add(domainFactory.createStudyGroupWordBook((long)i));
        });

        long userId = 5L;

        //when
        StudyGroupDetailDTO studyGroupDetailDTO = studyToStudyGroupDetailDtoMapper.entityToStudGroupResponseDTO(studyGroup.getStudyList().get(1));

        //then
        logger.info(objectMapper.writeValueAsString(studyGroupDetailDTO));

        assertThat(studyGroupDetailDTO.getId()).isEqualTo(studyGroup.getId());
        assertThat(studyGroupDetailDTO.getName()).isEqualTo(studyGroup.getName());

        IntStream.range(0, length).forEach((i) -> {
            User user = studyGroup.getStudyList().get(i).getUser();
            StudyGroupDetailDTO.UserDTO userDTO = studyGroupDetailDTO.getUserDTOList().get(i);

            assertThat(userDTO.getId()).isEqualTo(user.getId());
            assertThat(userDTO.getName()).isEqualTo(user.getName());

            StudyGroupWordBook studyGroupWordBook = studyGroup.getStudyGroupWordBookList().get(i);
            StudyGroupDetailDTO.WordBookDTO wordBookDTO = studyGroupDetailDTO.getWordBookDTOList().get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(studyGroupWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(studyGroupWordBook.getName());
        });
    }

    @Test
    @DisplayName("StudyList를 UserDTOList로 맵핑이 정상적으로 동작 하는 테스트")
    void mapToUserDTOList() {
        //given
        StudyGroup studyGroup = domainFactory.createStudyGroup(0L);

        int userSize = 3;
        IntStream.range(0, userSize).forEach((i) -> {
            User user = domainFactory.createUser(i);

            Study study = Study.builder()
                    .id((long) i)
                    .studyGroup(studyGroup)
                    .studyGroupRole(StudyGroupRole.ADMIN)
                    .user(user)
                    .build();

            studyGroup.addStudy(study);
        });

        //when
        List<StudyGroupDetailDTO.UserDTO> userDTOList = studyToStudyGroupDetailDtoMapper.mapToUserDTOList(studyGroup.getStudyList());

        //then
        IntStream.range(0, userSize).forEach((i) -> {
            User user = studyGroup.getStudyList().get(i).getUser();

            assertThat(userDTOList.get(i).getId()).isEqualTo(user.getId());
            assertThat(userDTOList.get(i).getName()).isEqualTo(user.getName());
        });
    }

    @Test
    @DisplayName("StudyList를 UserDTOList로 맵핑이 정상적으로 동작 하는 테스트")
    void mapToWordBookDTOList() throws Exception {
        //given
        StudyGroup studyGroup = domainFactory.createStudyGroup(0L);

        int userSize = 3;
        IntStream.range(0, userSize).forEach((i) -> {
            StudyGroupWordBook studyGroupWordBook = domainFactory.createStudyGroupWordBook((long)i);
            studyGroup.getStudyGroupWordBookList().add(studyGroupWordBook);
        });

        //when
        List<StudyGroupDetailDTO.WordBookDTO> wordBookDTOList = studyToStudyGroupDetailDtoMapper.mapToWordBookDTOList(studyGroup.getStudyGroupWordBookList());

        //then
        IntStream.range(0, userSize).forEach((i) -> {
            StudyGroupWordBook studyGroupWordBook = studyGroup.getStudyGroupWordBookList().get(i);
            assertThat(wordBookDTOList.get(i).getId()).isEqualTo(studyGroupWordBook.getId());
            assertThat(wordBookDTOList.get(i).getId()).isEqualTo(studyGroupWordBook.getId());
        });
    }

}