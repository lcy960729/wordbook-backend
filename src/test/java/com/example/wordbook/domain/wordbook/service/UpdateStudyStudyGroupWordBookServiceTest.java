package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.studyGroup.entity.StudyGroup;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.UpdateStudyGroupWordBookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UpdateStudyStudyGroupWordBookServiceTest {
//
//    @InjectMocks
//    private UpdateStudyGroupWordBookService updateStudyGroupWordBookService;
//
//    @Mock
//    private GetStudyGroupWordBookService getStudyGroupWordBookService;
//
//    @Mock
//    private WordBookRepository wordBookRepository;
//
//    private final Long wordBookId = 0L;
//    private StudyGroupWordBook studyGroupWordBook;
//
//    @BeforeAll
//    public void createGroupWordBook() {
//        String nameConsistingWellFormed = "WordBook";
//        StudyGroup studyGroup = StudyGroup.builder().build();
//
//        studyGroupWordBook = StudyGroupWordBook.builder()
//                .isUsing(true)
//                .name(nameConsistingWellFormed)
//                .studyGroup(studyGroup)
//                .build();
//        studyGroupWordBook.setId(wordBookId);
//    }
//
//    @Test
//    @DisplayName("정상적으로 단어장의 이름을 변경하는 테스트")
//    public void updateWordBookName() {
//        //given
//        String nameConsistingWellFormedAfterUpdate = "UpdateWordBook";
//
//        UpdateWordBookDTO updateGroupWordBookDTO = UpdateWordBookDTO.builder()
//                .name(nameConsistingWellFormedAfterUpdate)
//                .build();
//
//        given(getStudyGroupWordBookService.getEntityById(wordBookId)).willReturn(studyGroupWordBook);
//        given(wordBookRepository.save(any(StudyGroupWordBook.class))).willReturn(studyGroupWordBook);
//
//        //when
////        WordBookDetailDTO detailGroupWordBookDTO = updateStudyGroupWordBookService.update_name(wordBookId, updateGroupWordBookDTO);
//
//        //then
////        assertThat(detailGroupWordBookDTO.getId()).isEqualTo(wordBookId);
////        assertThat(detailGroupWordBookDTO.getName()).isEqualTo(nameConsistingWellFormedAfterUpdate);
//    }
//
//    @Test
//    @DisplayName("이름에 문자+숫자 조합이 아닌 숫자만 들어왔을때 에러를 반환하는 테스트")
//    public void updateWordBookName_ErrorTest_NameThatIsOnlyNumeric() {
//        //given
//        String nameConsistingWellFormedAfterUpdate = "123123";
//
//        UpdateWordBookDTO updateGroupWordBookDTO = UpdateWordBookDTO.builder()
////                .id(wordBookId)
//                .name(nameConsistingWellFormedAfterUpdate)
//                .build();
//
//        given(getStudyGroupWordBookService.getEntityById(wordBookId)).willReturn(studyGroupWordBook);
//        given(wordBookRepository.save(any(StudyGroupWordBook.class))).willReturn(studyGroupWordBook);
//
//        //when
//        Throwable throwable = catchThrowable(()-> updateStudyGroupWordBookService.update_name(wordBookId, updateGroupWordBookDTO));
//
//        //then
//        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
//    }
}