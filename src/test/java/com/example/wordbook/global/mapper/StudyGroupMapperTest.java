package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.dto.response.UserDetailResponseDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.global.tool.DomainFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;


@SpringBootTest(classes = {DomainFactory.class, ObjectMapper.class, StudyGroupMapperImpl.class})
class StudyGroupMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(StudyGroupMapperTest.class);

    @Autowired
    private DomainFactory domainFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudyGroupMapper studyGroupMapper;

    @Test
    @DisplayName("createDTOToEntity 맵핑이 정상적으로 동작 하는 테스트")
    void createDTOToEntity() {
        //given
        CreateStudyGroupRequestDTO createStudyGroupRequestDTO = CreateStudyGroupRequestDTO.builder()
                .name("testName")
                .build();

        StudyGroup studyGroup = studyGroupMapper.createDTOToEntity(createStudyGroupRequestDTO);

        assertThat(studyGroup).isNotNull();
        assertThat(studyGroup.getName())
                .isEqualTo(createStudyGroupRequestDTO.getName());
    }

    @Test
    @DisplayName("entityToDetailDTO 맵핑이 정상적으로 동작 하는 테스트")
    void entityToDetailDTO() throws JsonProcessingException {
        //given
        StudyGroup studyGroup = domainFactory.createStudyGroup(0L);

        int length = 3;
        IntStream.range(0, length).forEach((i) -> {
            User user = domainFactory.createUser(i);

            Study study = Study.builder()
                    .id((long) i)
                    .studyGroup(studyGroup)
                    .user(user)
                    .build();

            studyGroup.addStudy(study);
            studyGroup.getStudyGroupWordBookList().add(domainFactory.createStudyGroupWordBook(i));
        });
        long userId = 5L;

        //when
        StudyGroupDetailResponseDTO studyGroupDetailResponseDTO = studyGroupMapper.entityToDetailDTO(userId, studyGroup);

        //then
        logger.info(objectMapper.writeValueAsString(studyGroupDetailResponseDTO));

        assertThat(studyGroupDetailResponseDTO.getId()).isEqualTo(studyGroup.getId());
        assertThat(studyGroupDetailResponseDTO.getName()).isEqualTo(studyGroup.getName());

        IntStream.range(0, length).forEach((i) -> {
            User user = studyGroup.getStudyList().get(i).getUser();
            StudyGroupDetailResponseDTO.UserDTO userDTO = studyGroupDetailResponseDTO.getUserList().get(i);

            assertThat(userDTO.getId()).isEqualTo(user.getId());
            assertThat(userDTO.getName()).isEqualTo(user.getName());

            StudyGroupWordBook studyGroupWordBook = studyGroup.getStudyGroupWordBookList().get(i);
            StudyGroupDetailResponseDTO.WordBookDTO wordBookDTO = studyGroupDetailResponseDTO.getWordBookList().get(i);

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
                    .user(user)
                    .build();

            studyGroup.addStudy(study);
        });

        //when
        List<StudyGroupDetailResponseDTO.UserDTO> userDTOList = studyGroupMapper.mapToUserDTOList(studyGroup.getStudyList());

        //then
        IntStream.range(0, userSize).forEach((i) -> {
            User user = studyGroup.getStudyList().get(i).getUser();

            assertThat(userDTOList.get(i).getId()).isEqualTo(user.getId());
            assertThat(userDTOList.get(i).getName()).isEqualTo(user.getName());
        });
    }

    @Test
    @DisplayName("StudyList를 UserDTOList로 맵핑이 정상적으로 동작 하는 테스트")
    void mapToWordBookDTOList() {
        //given
        StudyGroup studyGroup = domainFactory.createStudyGroup(0L);

        int userSize = 3;
        IntStream.range(0, userSize).forEach((i) -> {
            StudyGroupWordBook studyGroupWordBook = domainFactory.createStudyGroupWordBook(i);
            studyGroup.getStudyGroupWordBookList().add(studyGroupWordBook);
        });

        //when
        List<StudyGroupDetailResponseDTO.WordBookDTO> wordBookDTOList = studyGroupMapper.mapToWordBookDTOList(studyGroup.getStudyGroupWordBookList());

        //then
        IntStream.range(0, userSize).forEach((i) -> {
            StudyGroupWordBook studyGroupWordBook = studyGroup.getStudyGroupWordBookList().get(i);
            assertThat(wordBookDTOList.get(i).getId()).isEqualTo(studyGroupWordBook.getId());
            assertThat(wordBookDTOList.get(i).getId()).isEqualTo(studyGroupWordBook.getId());
        });
    }
}