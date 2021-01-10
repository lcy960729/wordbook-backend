package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.entity.GroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.CreateGroupWordBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CreateGroupWordBookServiceTest {

    @InjectMocks
    private CreateGroupWordBookService createGroupWordBookService;

    @Mock
    private WordBookRepository wordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createGroupWordBook() {
        //given
        WordBookRequestDTO.Create createGroupWordBookDTO = WordBookRequestDTO.Create.builder()
                .name("cy")
                .ownerId(0L)
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(wordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Long id = createGroupWordBookService.create(createGroupWordBookDTO);

        //then
        verify(wordBookRepository).save(any());
        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createGroupWordBook_Bad_Request_Empty_Input() {
        //given
        WordBookRequestDTO.Create createGroupWordBookDTO = WordBookRequestDTO.Create.builder()
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(wordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createGroupWordBookService.create(createGroupWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createGroupWordBook_ErrorTest_WrongInput() {
        //given
        WordBookRequestDTO.Create createGroupWordBookDTO = WordBookRequestDTO.Create.builder()
                .name("12341234")
                .ownerId(0L)
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(wordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createGroupWordBookService.create(createGroupWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}