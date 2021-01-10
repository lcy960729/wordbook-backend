package com.example.wordbook.domain.wordbook.service;

import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.CreateGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.DeleteGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.GetGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.UpdateGroupWordBookService;
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
import org.springframework.stereotype.Service;

@Component
public class WordBookServiceFactory {

    @Autowired
    private CreateGroupWordBookService createGroupWordBookService;
    @Autowired
    private CreateUserWordBookService createUserWordBookService;
    @Autowired
    private GetGroupWordBookService getGroupWordBookService;
    @Autowired
    private GetUserWordBookService getUserWordBookService;
    @Autowired
    private UpdateGroupWordBookService updateGroupWordBookService;
    @Autowired
    private UpdateUserWordBookService updateUserWordBookService;
    @Autowired
    private DeleteGroupWordBookService deleteGroupWordBookService;
    @Autowired
    private DeleteUserWordBookService deleteUserWordBookService;

    public CreateWordBookService getCreateServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return createUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return createGroupWordBookService;
        } else
            return null;
    }

    public GetWordBookService getGetServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return getUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return getGroupWordBookService;
        } else
            return null;
    }

    public UpdateWordBookService getUpdateServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return updateUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return updateGroupWordBookService;
        } else
            return null;
    }

    public DeleteWordBookService getDeleteServiceImpl(WordBookType wordBookType) {
        if (wordBookType == WordBookType.USER) {
            return deleteUserWordBookService;
        } else if (wordBookType == WordBookType.GROUP) {
            return deleteGroupWordBookService;
        } else
            return null;
    }

}
