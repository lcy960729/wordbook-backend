package com.example.wordbook.domain.userwordbook.service;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.entity.UserWordBook;
import com.example.wordbook.domain.userwordbook.repository.UserWordBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CreateUserWordBookServiceTest {

    @InjectMocks
    private CreateUserWordBookService createUserWordBookService;

    @Mock
    private UserWordBookRepository userWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createUserWordBook() {
        //given
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create.builder()
                .name("cy")
                .userId(0L)
                .build();
        UserWordBook userWordBook = modelMapper.map(createUserWordBookDTO, UserWordBook.class);
        userWordBook.setId(1L);

        given(userWordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        Long id = createUserWordBookService.create(createUserWordBookDTO);

        //then
        verify(userWordBookRepository).save(any());
        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createUserWordBook_Bad_Request_Empty_Input() {
        //given
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create.builder()
                .build();
        UserWordBook userWordBook = modelMapper.map(createUserWordBookDTO, UserWordBook.class);
        userWordBook.setId(1L);

        given(userWordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createUserWordBookService.create(createUserWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createUserWordBook_ErrorTest_WrongInput() {
        //given
        UserWordBookRequestDTO.Create createUserWordBookDTO = UserWordBookRequestDTO.Create.builder()
                .name("12341234")
                .userId(0L)
                .build();
        UserWordBook userWordBook = modelMapper.map(createUserWordBookDTO, UserWordBook.class);
        userWordBook.setId(1L);

        given(userWordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createUserWordBookService.create(createUserWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}