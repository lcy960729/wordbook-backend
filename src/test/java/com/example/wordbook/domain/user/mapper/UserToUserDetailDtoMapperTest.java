package com.example.wordbook.domain.user.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.global.enums.StudyGroupRole;
import com.example.wordbook.global.tool.DomainFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class UserToUserDetailDtoMapperTest {

    private final UserToUserDetailDtoMapper userToUserDetailDtoMapper = Mockito.spy(Mappers.getMapper(UserToUserDetailDtoMapper.class));

    private final DomainFactory domainFactory = new DomainFactory();

    @Test
    @DisplayName("userToUserDetailDTO 맵핑이 정삭적으로 동작 하는 테스트")
    void entityToUserDetailDTO() {
        //given
        User user = domainFactory.getUserOfStudyGroupAdmin();

        //when
        UserDetailDTO userDetailDTO = userToUserDetailDtoMapper.entityToUserDetailDTO(user);

        //then
        assertThat(userDetailDTO.getId()).isEqualTo(user.getId());
        assertThat(userDetailDTO.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDetailDTO.getName()).isEqualTo(user.getName());

        int studyGroupListSize = user.getStudyList().size();
        IntStream.range(0, studyGroupListSize).forEach((i) -> {
            UserDetailDTO.StudyGroupDTO studyGroupDTO = userDetailDTO.getStudyGroupDTOList().get(i);
            StudyGroup studyGroup = user.getStudyList().get(i).getStudyGroup();

            assertThat(studyGroupDTO.getId()).isEqualTo(studyGroup.getId());
            assertThat(studyGroupDTO.getName()).isEqualTo(studyGroup.getName());
        });

        int userWordBookListSize = user.getUserWordBookList().size();
        IntStream.range(0, userWordBookListSize).forEach((i) -> {
            UserDetailDTO.WordBookDTO wordBookDTO = userDetailDTO.getWordBookDTOList().get(i);
            UserWordBook userWordBook = user.getUserWordBookList().get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(userWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(userWordBook.getName());
        });
    }

    @Test
    @DisplayName("User를 StudyGroupDTOList 맵핑이 정삭적으로 동작 하는 테스트")
    void mapToStudyGroupDTOList() {
        //given
        User user = domainFactory.getUserOfStudyGroupAdmin();

        //when
        List<UserDetailDTO.StudyGroupDTO> studyGroupDTOList = userToUserDetailDtoMapper.mapToStudyGroupDTOList(user.getStudyList());

        //then
        int studyGroupListSize = user.getStudyList().size();
        IntStream.range(0, studyGroupListSize).forEach((i) -> {
            UserDetailDTO.StudyGroupDTO studyGroupDTO = studyGroupDTOList.get(i);
            StudyGroup studyGroup = user.getStudyList().get(i).getStudyGroup();

            assertThat(studyGroupDTO.getId()).isEqualTo(studyGroup.getId());
            assertThat(studyGroupDTO.getName()).isEqualTo(studyGroup.getName());
        });
    }

    @Test
    @DisplayName("userWordBookList를 userWordBookDTOList로 맵핑이 정삭적으로 동작 하는 테스트")
    void mapToWordBookDTOList() {
        //given
        List<UserWordBook> userWordBookList = domainFactory.getUserOfStudyGroupAdmin().getUserWordBookList();

        //when
        List<UserDetailDTO.WordBookDTO> wordBookDTOList = userToUserDetailDtoMapper.mapToWordBookDTOList(userWordBookList);

        //then
        int userWordBookListSize = userWordBookList.size();
        IntStream.range(0, userWordBookListSize).forEach((i) -> {
            UserDetailDTO.WordBookDTO wordBookDTO = wordBookDTOList.get(i);
            UserWordBook userWordBook = userWordBookList.get(i);

            assertThat(wordBookDTO.getId()).isEqualTo(userWordBook.getId());
            assertThat(wordBookDTO.getName()).isEqualTo(userWordBook.getName());
        });
    }

    @Test
    @DisplayName("맵핑 후 makeLinks 함수가 정상적으로 동작하는 테스트")
    void makeLinksAfterMapping() {
        entityToUserDetailDTO();
        verify(userToUserDetailDtoMapper).makeLinksAfterMapping(any(UserDetailDTO.class));
    }
}