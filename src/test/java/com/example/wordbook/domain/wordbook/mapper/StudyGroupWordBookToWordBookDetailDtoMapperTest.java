package com.example.wordbook.domain.wordbook.mapper;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class StudyGroupWordBookToWordBookDetailDtoMapperTest {

    private final StudyGroupWordBookToWordBookDetailDtoMapper studyGroupWordBookToWordBookDetailDtoMapper
            = Mockito.spy(Mappers.getMapper(StudyGroupWordBookToWordBookDetailDtoMapper.class));

    private final DomainFactory domainFactory = new DomainFactory();

    @Test
    @DisplayName("StudyGroupWordBook을 WordBookDetailDTO로 맵핑이 정상적으로 동작하는 테스트")
    void entityToResponseDetailDTO() {
        //given
        Study study = domainFactory.getStudyOfStudyGroupNormal();
        User user = study.getUser();
        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        //when
        WordBookDetailDTO wordBookDetailDTO = studyGroupWordBookToWordBookDetailDtoMapper.entityToResponseDetailDTO(user.getId(), studyGroupWordBook);

        //then
        assertThat(wordBookDetailDTO).isNotNull();
        assertThat(wordBookDetailDTO.getId()).isEqualTo(studyGroupWordBook.getId());
        assertThat(wordBookDetailDTO.getName()).isEqualTo(studyGroupWordBook.getName());

        int wordListSize = studyGroupWordBook.getWords().size();
        IntStream.range(0, wordListSize).forEach(i -> {
            WordBookDetailDTO.WordDTO wordDTO = wordBookDetailDTO.getWordDTOList().get(i);
            Word word = studyGroupWordBook.getWords().get(i);

            assertThat(word.getId()).isEqualTo(wordDTO.getId());
            assertThat(word.getVoca()).isEqualTo(wordDTO.getVoca());
            assertThat(word.getMeaning()).isEqualTo(wordDTO.getMeaning());
        });
    }

    @Test
    @DisplayName("WordListToWordDTOList 맵핑이 정상적으로 동작 하는 테스트")
    void mapToWordDTOList() {
        //given
        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();
        List<Word> wordList = studyGroupWordBook.getWords();

        //when
        List<WordBookDetailDTO.WordDTO> wordDTOList = studyGroupWordBookToWordBookDetailDtoMapper.mapToWordDTOList(wordList);

        //then
        assertThat(wordDTOList).isNotNull();

        IntStream.range(0, wordList.size()).forEach(i -> {
            WordBookDetailDTO.WordDTO wordDTO = wordDTOList.get(i);
            Word word = wordList.get(i);

            assertThat(word.getId()).isEqualTo(wordDTO.getId());
            assertThat(word.getVoca()).isEqualTo(wordDTO.getVoca());
            assertThat(word.getMeaning()).isEqualTo(wordDTO.getMeaning());
        });
    }

    @Test
    @DisplayName("맵핑 후 makeLinks 함수가 정상적으로 동작하는 테스트")
    void makeLinks() {
        //when
        entityToResponseDetailDTO();

        //then
        verify(studyGroupWordBookToWordBookDetailDtoMapper).makeLinks(
                any(WordBookDetailDTO.class),
                anyLong(),
                any(StudyGroupWordBook.class));
    }
}