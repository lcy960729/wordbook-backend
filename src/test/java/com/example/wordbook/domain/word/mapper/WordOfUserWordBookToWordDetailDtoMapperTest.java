package com.example.wordbook.domain.word.mapper;

import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class WordOfUserWordBookToWordDetailDtoMapperTest {

    private final WordOfUserWordBookToWordDetailDtoMapper wordOfUserWordBookToWordDetailDtoMapper
            = Mockito.spy(Mappers.getMapper(WordOfUserWordBookToWordDetailDtoMapper.class));

    @Test
    @DisplayName("그룹 단어장의 Word To WordDetailDTO 맵핑이 정상적으로 동작하는 테스트")
    void entityToDetailDTO() {
        //given
        Word word = Word.builder()
                .isUsing(true)
                .voca("test")
                .meaning("테스트")
                .id(0L)
                .build();

        //when
        WordDetailDTO wordDetailDTO = wordOfUserWordBookToWordDetailDtoMapper.entityToDetailDTO(word, 0L, 1L);

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
        verify(wordOfUserWordBookToWordDetailDtoMapper).makeLinks(any(WordDetailDTO.class), anyLong(), anyLong());
    }
}