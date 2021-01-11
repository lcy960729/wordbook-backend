package com.example.wordbook.domain.wordbook.service.groupwordbookImpl;

import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import com.example.wordbook.domain.wordbook.repository.WordBookRepository;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudyGroupWordBookService implements DeleteWordBookService {

    private final WordBookRepository wordBookRepository;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;

    public DeleteStudyGroupWordBookService(WordBookRepository wordBookRepository, GetStudyGroupWordBookService getStudyGroupWordBookService) {
        this.wordBookRepository = wordBookRepository;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
    }

    public void deleteById(Long id){
        StudyGroupWordBook studyGroupWordBook = getStudyGroupWordBookService.getEntityById(id);

        wordBookRepository.delete(studyGroupWordBook);
    }
}
