package com.example.wordbook.domain.word.mapper;

import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.global.tool.DomainFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class WordOfStudyGroupWordBookToWordDetailDtoMapperTest {

    private final WordOfStudyGroupWordBookToWordDetailDtoMapper wordOfStudyGroupWordBookToWordDetailDtoMapper =
            Mockito.spy(Mappers.getMapper(WordOfStudyGroupWordBookToWordDetailDtoMapper.class));

    @Test
    @DisplayName("개인단어장의 Word To WordDetailDTO 맵핑이 정상적으로 동작하는 테스트")
    void entityToDetailDTO() {
        //given
        Word word = Word.builder()
                .isUsing(true)
                .voca("test")
                .meaning("테스트")
                .id(0L)
                .build();

        //when
        WordDetailDTO wordDetailDTO = wordOfStudyGroupWordBookToWordDetailDtoMapper.entityToDetailDTO(word, 0L, 1L, 2L);

        //then
        assertThat(wordDetailDTO).isNotNull();
        assertThat(wordDetailDTO.getVoca()).isEqualTo(word.getVoca());
        assertThat(wordDetailDTO.getMeaning()).isEqualTo(word.getMeaning());
    }

    @Test
    @DisplayName("맵핑된 후 makeLinks 함수가 정상적으로 동작하는 테스트")
    void makeLinks() {
        //when
        entityToDetailDTO();

        //then
        verify(wordOfStudyGroupWordBookToWordDetailDtoMapper).makeLinks(any(WordDetailDTO.class), anyLong(), anyLong(), anyLong());
    }
}