package com.example.wordbook.domain.groupwordbook.service;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.entity.GroupWordBook;
import com.example.wordbook.domain.groupwordbook.repository.GroupWordBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
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
public class CreateGroupWordBookServiceTest {

    @InjectMocks
    private CreateGroupWordBookService createGroupWordBookService;

    @Mock
    private GroupWordBookRepository groupWordBookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("정상적으로 단어장을 생성하는 테스트")
    public void createGroupWordBook() {
        //given
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create.builder()
                .name("cy")
                .groupId(0L)
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(groupWordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Long id = createGroupWordBookService.create(createGroupWordBookDTO);

        //then
        verify(groupWordBookRepository).save(any());
        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
    public void createGroupWordBook_Bad_Request_Empty_Input() {
        //given
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create.builder()
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(groupWordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createGroupWordBookService.create(createGroupWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
    public void createGroupWordBook_ErrorTest_WrongInput() {
        //given
        GroupWordBookRequestDTO.Create createGroupWordBookDTO = GroupWordBookRequestDTO.Create.builder()
                .name("12341234")
                .groupId(0L)
                .build();
        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
        groupWordBook.setId(1L);

        given(groupWordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);

        //when
        Throwable throwable = catchThrowable(() -> createGroupWordBookService.create(createGroupWordBookDTO));

        //then
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}