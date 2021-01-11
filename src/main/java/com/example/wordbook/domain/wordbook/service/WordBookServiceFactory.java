package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.CreateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.DeleteStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.UpdateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.CreateUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.DeleteUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.GetUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.UpdateUserWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordBookServiceFactory {

    @Autowired
    private CreateStudyGroupWordBookService createStudyGroupWordBookService;
    @Autowired
    private CreateUserWordBookService createUserWordBookService;
    @Autowired
    private GetStudyGroupWordBookService getStudyGroupWordBookService;
    @Autowired
    private GetUserWordBookService getUserWordBookService;
    @Autowired
    private UpdateStudyGroupWordBookService updateStudyGroupWordBookService;
    @Autowired
    private UpdateUserWordBookService updateUserWordBookService;
    @Autowired
    private DeleteStudyGroupWordBookService deleteStudyGroupWordBookService;
    @Autowired
    private DeleteUserWordBookService deleteUserWordBookService;

    public CreateWordBookService getCreateServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return createUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return createStudyGroupWordBookService;
        } else
            return null;
    }

    public GetWordBookService getGetServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return getUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return getStudyGroupWordBookService;
        } else
            return null;
    }

    public UpdateWordBookService getUpdateServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return updateUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return updateStudyGroupWordBookService;
        } else
            return null;
    }

    public DeleteWordBookService getDeleteServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return deleteUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return deleteStudyGroupWordBookService;
        } else
            return null;
    }

}
