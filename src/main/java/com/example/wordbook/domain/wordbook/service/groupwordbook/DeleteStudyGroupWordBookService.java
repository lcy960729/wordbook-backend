package com.example.wordbook.domain.wordbook.service.groupwordbook;

import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.NotUseClass.wordbook.DeleteWordBookService;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudyGroupWordBookService  {

    private final WordBookRepository wordBookRepository;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public DeleteStudyGroupWordBookService(WordBookRepository wordBookRepository, GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.wordBookRepository = wordBookRepository;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public void delete(Long adminId, Long studyGroupId, Long wordBookId){
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityByAdminIdAndStudyGroupIdAndWordBookId(adminId, studyGroupId, wordBookId);

        wordBookRepository.delete(studyGroupWordBook);
    }
}
