//package com.example.wordbook.domain.word.service;
//
//import com.example.wordbook.domain.word.dto.WordRequestDTO;
//import com.example.wordbook.domain.word.entity.Word;
//import com.example.wordbook.domain.word.repository.WordRepository;
//import com.example.wordbook.domain.wordbook.entity.WordBook;
//import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.crossstore.ChangeSetPersister;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class CreateWordServiceTest {
//
//    @InjectMocks
//    private CreateWordService createWordService;
//
//    @Mock
//    private WordRepository wordRepository;
//
//    @Mock
//    private WordBookRepository wordBookRepository;
//
//    private final ModelMapper modelMapper = new ModelMapper();
//
//    @Test
//    public void createWord_onUserWordBook() throws ChangeSetPersister.NotFoundException {
//        //given
//        WordBook wordBook = new WordBook(0L, true, "임시", new ArrayList<>());
//
//        given(wordBookRepository.findById(anyLong())).willReturn(Optional.of(wordBook));
//        given(wordBookRepository.save(any(WordBook.class))).willReturn(wordBook);
//
//        WordRequestDTO.Create createWordDTO = WordRequestDTO.Create.builder()
//                .voca("apple")
//                .meaning("사과")
//                .wordBookId(0L)
//                .build();
//
//        Word word = modelMapper.map(createWordDTO, Word.class);
//        word.setUsing(true);
//        word.setId(0L);
//        word.setWordBook(wordBook);
//
//        given(wordRepository.save(any(Word.class))).willReturn(word);
//
//        //when
//        Long id = createWordService.create(createWordDTO);
//
//        //then
//        verify(wordRepository).save(any());
//        verify(wordBookRepository).save(any());
//        assertThat(id).isNotNull();
//    }
//}