package com.example.wordbook.domain.studyGroup.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.global.enums.DomainLink;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;

class StudyToStudyGroupDetailDtoMapperTest {

    private final StudyToStudyGroupDetailDtoMapper studyToStudyGroupDetailDtoMapper = Mockito.spy(Mappers.getMapper(StudyToStudyGroupDetailDtoMapper.class));

    private final DomainFactory domainFactory = new DomainFactory();

    @Test
    @DisplayName("Study To StudyGroupDetailDTO 맵핑이 정삭적으로 동작 하는 테스트")
    void entityToStudGroupResponseDTO() {
        //given
        Study study = domainFactory.getStudyOfStudyGroupNormal();
        StudyGroup studyGroup = study.getStudyGroup();

        //when
        StudyGroupDetailDTO studyGroupDetailDTO = studyToStudyGroupDetailDtoMapper
                .entityToStudGroupResponseDTO(study);

        //then
        assertThat(studyGroupDetailDTO).isNotNull();
        assertThat(studyGroupDetailDTO.getId()).isEqualTo(studyGroup.getId());
        assertThat(studyGroupDetailDTO.getName()).isEqualTo(studyGroup.getName());

        int userListSize = studyGroup.getStudyList().size();
        IntStream.range(0, userListSize).forEach((i) -> {
            StudyGroupDetailDTO.UserDTO userDTO = studyGroupDetailDTO.getUserDTOList().get(i);
            User user = studyGroup.getStudyList().get(i).getUser();

            assertThat(userDTO.getId()).isEqualTo(user.getId());
            assertThat(userDTO.getName()).isEqualTo(user.getName());
        });

        int wordBookListSize = studyGroup.getStudyGroupWordBookList().size();
        IntStream.range(0, wordBookListSize).forEach((i) -> {
            StudyGroupDetailDTO.UserDTO userDTO = studyGroupDetailDTO.getUserDTOList().get(i);
            User user = studyGroup.getStudyList().get(i).getUser();

            assertThat(userDTO.getId()).isEqualTo(user.getId());
            assertThat(userDTO.getName()).isEqualTo(user.getName());
        });
    }

    @Test
    @DisplayName("StudyList To UserDTOList 맵핑이 정상 동작 하는 테스트")
    void mapToUserDTOList() {
        List<Study> studyList = domainFactory.getStudyGroup().getStudyList();

        List<StudyGroupDetailDTO.UserDTO> userDTOList = studyToStudyGroupDetailDtoMapper.mapToUserDTOList(studyList);

        IntStream.range(0, studyList.size()).forEach((i) -> {
            StudyGroupDetailDTO.UserDTO userDTO = userDTOList.get(i);
            User user = studyList.get(i).getUser();

            assertThat(userDTO.getId()).isEqualTo(user.getId());
            assertThat(userDTO.getName()).isEqualTo(user.getName());
        });
    }

    @Test
    @DisplayName("WordBookList To WordBookDTOList 맵핑이 정상 동작 하는 테스트")
    void mapToWordBookDTOList() {
        //given
        List<StudyGroupWordBook> studyGroupWordBookList = domainFactory.getStudyGroup().getStudyGroupWordBookList();

        //when
        List<StudyGroupDetailDTO.WordBookDTO> wordBookDTOList = studyToStudyGroupDetailDtoMapper.mapToWordBookDTOList(studyGroupWordBookList);

        //then
        IntStream.range(0, studyGroupWordBookList.size()).forEach((i) -> {
            StudyGroupDetailDTO.WordBookDTO wordBookDTO = wordBookDTOList.get(i);
            StudyGroupWordBook studyGroupWordBook = studyGroupWordBookList.get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(studyGroupWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(studyGroupWordBook.getName());
        });
    }

    @Test
    @DisplayName("맵핑이 진행된 후 AfterMapping 함수 동작 확인 테스트")
    void makeLinksAfterMapping() {
        //when
        entityToStudGroupResponseDTO();

        //then
        verify(studyToStudyGroupDetailDtoMapper).makeLinksAfterMapping(any(StudyGroupDetailDTO.class), any(Study.class));
    }
}