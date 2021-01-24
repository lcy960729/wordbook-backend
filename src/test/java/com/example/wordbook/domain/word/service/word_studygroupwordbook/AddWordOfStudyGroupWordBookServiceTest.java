package com.example.wordbook.domain.word.service.word_studygroupwordbook;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.word.controller.word_studygroupwordbook.WordOfStudyGroupWordBookControllerTest;
import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.mapper.CreateDtoToWordMapper;
import com.example.wordbook.domain.word.mapper.WordOfStudyGroupWordBookToWordDetailDtoMapper;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.word.service.studygroupwordbook.AddWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.service.studygroupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.global.enums.DomainLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddWordOfStudyGroupWordBookServiceTest extends WordOfStudyGroupWordBookServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private GetStudyGroupWordBookService getStudyGroupWordBookService;

    @Test
    @DisplayName("정상적으로 그룹단어장에 단어를 추가하는 테스트")
    public void addWordAtStudyGroupWordBook() {
        //given
        AddWordOfStudyGroupWordBookService addWordOfStudyGroupWordBookService = new AddWordOfStudyGroupWordBookService(
                wordRepository,
                getStudyGroupWordBookService,
                wordOfStudyGroupWordBookToWordDetailDtoMapper,
                createDtoToWordMapper);

        CreateWordDTO createWordDTO = CreateWordDTO.builder()
                .voca("test")
                .meaning("테스트")
                .build();

        Word word = createDtoToWordMapper.createDTOToEntity(createWordDTO);
        word.setId(0L);

        Study study = domainFactory.getStudyOfStudyGroupNormal();
        StudyGroupWordBook studyGroupWordBook = domainFactory.getStudyGroupWordBook();

        given(wordRepository.save(any(Word.class))).willReturn(word);
        given(getStudyGroupWordBookService.getEntityByUserIdAndStudyGroupIdAndWordBookId(anyLong(), anyLong(), anyLong())).willReturn(studyGroupWordBook);

        //when
        Long user = study.getUser().getId();
        Long studyGroupId = study.getStudyGroup().getId();
        Long wordBookId = studyGroupWordBook.getId();

        WordDetailDTO wordDetailDTO = addWordOfStudyGroupWordBookService.add(user, studyGroupId, wordBookId, createWordDTO);

        //then
        assertThat(wordDetailDTO).isNotNull();
        assertThat(wordDetailDTO.getId()).isEqualTo(word.getId());
    }
}
