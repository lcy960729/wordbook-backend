package com.example.wordbook.global.mapper;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.mapper.UserToUserDetailDtoMapper;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
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

@SpringBootTest(classes = {DomainFactory.class, ObjectMapper.class, UserToUserDetailDtoMapperImpl.class})
class UserToUserDetailDtoMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(UserToUserDetailDtoMapperTest.class);

    @Autowired
    private DomainFactory domainFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserToUserDetailDtoMapper userToUserDetailDtoMapper;

    @Test
    @DisplayName("createUserDTOToEntity 맵핑이 정삭적으로 동작 하는 테스트")
    void createUserDTOToEntity() throws JsonProcessingException {
        //given
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("testName")
                .email("testEmail")
                .pw("testPw")
                .build();

        //when
        User user = userToUserDetailDtoMapper.createUserDTOToEntity(createUserDTO);

        //then
        logger.info(objectMapper.writeValueAsString(user));

        assertThat(user.getName()).isEqualTo(createUserDTO.getName());
        assertThat(user.getEmail()).isEqualTo(createUserDTO.getEmail());
    }

    @Test
    @DisplayName("userToUserDetailDTO 맵핑이 정삭적으로 동작 하는 테스트")
    void entityToUserDetailDTO() throws Exception {
        //given
        long userId = 0L;
        User user = domainFactory.createUser(userId);

        int length = 3;
        IntStream.range(0, length).forEach((i) -> {
            StudyGroup studyGroup = domainFactory.createStudyGroup(i);
            UserWordBook userWordBook = domainFactory.createUserWordBook((long) i);

            Study study = Study.builder()
                    .id((long) i)
                    .studyGroup(studyGroup)
                    .user(user)
                    .studyGroupRole(StudyGroupRole.ADMIN)
                    .build();

            user.joinToStudy(study);
            user.getUserWordBookList().add(userWordBook);
        });

        //when
        UserDetailDTO userDetailDTO = userToUserDetailDtoMapper.entityToUserDetailDTO(user);

        //then
        logger.info(objectMapper.writeValueAsString(userDetailDTO));

        assertThat(userDetailDTO.getId()).isEqualTo(user.getId());
        assertThat(userDetailDTO.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDetailDTO.getName()).isEqualTo(user.getName());

        IntStream.range(0, length).forEach((i) -> {
            UserDetailDTO.StudyGroupDTO studyGroupDTO = userDetailDTO.getStudyGroupDTOList().get(i);
            StudyGroup studyGroup = user.getStudyList().get(i).getStudyGroup();

            assertThat(studyGroupDTO.getId()).isEqualTo(studyGroup.getId());
            assertThat(studyGroupDTO.getName()).isEqualTo(studyGroup.getName());

            UserDetailDTO.WordBookDTO wordBookDTO = userDetailDTO.getWordBookDTOList().get(i);
            UserWordBook userWordBook = user.getUserWordBookList().get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(userWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(userWordBook.getName());
        });
    }

    @Test
    @DisplayName("User를 StudyGroupDTOList 맵핑이 정삭적으로 동작 하는 테스트")
    void mapToStudyGroupDTOList() throws Exception {
        //given
        User user = domainFactory.createUser(0L);

        int length = 5;
        IntStream.range(0, length).forEach((i) -> {
            StudyGroup studyGroup = domainFactory.createStudyGroup(i);

            Study study = Study.builder()
                    .id((long) i)
                    .user(user)
                    .studyGroup(studyGroup)
                    .studyGroupRole(StudyGroupRole.ADMIN)
                    .build();

            user.getStudyList().add(study);
        });

        //when
        List<UserDetailDTO.StudyGroupDTO> studyGroupDTOList = userToUserDetailDtoMapper.mapToStudyGroupDTOList(user.getStudyList());

        //then
        IntStream.range(0, length).forEach((i) -> {
            UserDetailDTO.StudyGroupDTO studyGroupDTO = studyGroupDTOList.get(i);
            StudyGroup studyGroup = user.getStudyList().get(i).getStudyGroup();

            assertThat(studyGroupDTO.getId()).isEqualTo(studyGroup.getId());
            assertThat(studyGroupDTO.getName()).isEqualTo(studyGroup.getName());
        });
    }

    @Test
    @DisplayName("userWordBookList를 userWordBookDTOList로 맵핑이 정삭적으로 동작 하는 테스트")
    void mapToWordBookDTOList() throws Exception {
        //given
        List<UserWordBook> userWordBookList = new ArrayList<>();

        int length = 5;
        IntStream.range(0, length).forEach((i) -> {
            userWordBookList.add(domainFactory.createUserWordBook((long) i));
        });

        //when
        List<UserDetailDTO.WordBookDTO> wordBookDTOList = userToUserDetailDtoMapper.mapToWordBookDTOList(userWordBookList);

        //then
        IntStream.range(0, length).forEach((i) -> {
            UserDetailDTO.WordBookDTO wordBookDTO = wordBookDTOList.get(i);
            UserWordBook userWordBook = userWordBookList.get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(userWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(userWordBook.getName());
        });
    }
}