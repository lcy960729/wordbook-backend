package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.user.entity.User;
import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.entity.UserWordBook;
import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.CreateUserWordBookService;
import com.example.wordbook.global.mapper.UserWordBookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CreateUserWordBookServiceTest {
//    // InjectMocks의 인젝션 방법 3개 1. 생성자, 프로퍼티, 필드
//    @Mock
//    private UserWordBookMapper userWordBookMapper;
//
//    @Mock
//    private WordBookRepository wordBookRepository;
//
//    private CreateUserWordBookService createUserWordBookService;
//
//    @BeforeEach
//    void setUp() {
//        createUserWordBookService = new CreateUserWordBookService(wordBookRepository, userWordBookMapper);
//    }
//
//    @Test
//    @DisplayName("정상적으로 단어장을 생성하는 테스트")
//    public void createUserWordBook() {
//        //given
//        Long ownerId = 0L;
//        User user = User.builder()
//                .id(ownerId)
//                .build();
//
//        String wordBookName = "testWordBookName";
//        Long wordBookId = 0L;
//
//        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO.builder()
//                .name(wordBookName)
//                .wordBookType(WordBookType.USER)
//                .ownerId(ownerId)
//                .build();
//
//        UserWordBook userWordBook = UserWordBook.builder()
//                .isUsing(true)
//                .id(wordBookId)
//                .user(user)
//                .words(new ArrayList<>())
//                .name(wordBookName)
//                .build();
//
//        given(userWordBookMapper.createDTOToEntity(any())).willReturn(userWordBook);
//        given(wordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);
//
//        //when
//        Long id = createUserWordBookService.create(createWordBookDTO);
//
//        //then
//        then(wordBookRepository).should().save(any(UserWordBook.class));
//        assertThat(id).isNotNull();
//    }
//
//    @Test
//    @DisplayName("입력값이 비어있을때 에러를 반환하는 테스트")
//    public void createGroupWordBook_Bad_Request_Empty_Input() {
//        //given
//        Long ownerId = 0L;
//        User user = User.builder()
//                .id(ownerId)
//                .build();
//
//        String wordBookName = "testWordBookName";
//        Long wordBookId = 0L;
//
//        CreateWordBookDTO createWordBookDTO = CreateWordBookDTO.builder()
//                .build();
//
//        UserWordBook userWordBook = UserWordBook.builder()
//                .build();
//
//        given(userWordBookMapper.createDTOToEntity(any())).willReturn(userWordBook);
//        given(wordBookRepository.save(any(UserWordBook.class))).willReturn(userWordBook);
//
//        //when
//        Throwable throwable = catchThrowable(() -> createUserWordBookService.create(createWordBookDTO));
//
//        //then
//        assertThat(throwable).isInstanceOf(Exception.class);
//    }
//
//    @Test
//    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
//    public void createGroupWordBook_ErrorTest_WrongInput() {
////        //given
////        WordBookRequestDTO.Create createGroupWordBookDTO = WordBookRequestDTO.Create.builder()
////                .name("12341234")
////                .ownerId(0L)
////                .build();
////        GroupWordBook groupWordBook = modelMapper.map(createGroupWordBookDTO, GroupWordBook.class);
////        groupWordBook.setId(1L);
////
////        given(wordBookRepository.save(any(GroupWordBook.class))).willReturn(groupWordBook);
//////
//////        //when
////        Throwable throwable = catchThrowable(() -> createGroupWordBookService.create(createGroupWordBookDTO));
//////
//////        //then
////        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
//    }
}