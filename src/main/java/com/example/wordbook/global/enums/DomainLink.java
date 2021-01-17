package com.example.wordbook.global.enums;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.word.controller.StudyGroupWordBookWordController;
import com.example.wordbook.domain.word.controller.UserWordBookWordController;
import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public enum DomainLink {
    SELF("self"),
    //User
    CREATE_USER("create_user"),
    GET_USER("get_user"),
    UPDATE_USER("update_user"),
    DELETE_USER("delete_user"),

    //Study Group
    CREATE_STUDY_GROUP("create_studyGroup"),
    GET_STUDY_GROUP("get_studyGroup"),
    UPDATE_STUDY_GROUP("update_studyGroup"),
    DELETE_STUDY_GROUP("delete_studyGroup"),

    //UserWordBook
    CREATE_USER_WORDBOOK("create_userWordBook"),
    GET_USER_WORDBOOK("get_userWordBook"),
    UPDATE_USER_WORDBOOK("update_userWordBook"),
    DELETE_USER_WORDBOOK("delete_userWordBook"),

    //StudyGroupWordBook
    CREATE_STUDY_GROUP_WORDBOOK("create_studyGroupWordBook"),
    GET_STUDY_GROUP_WORDBOOK("get_studyGroupWordBook"),
    UPDATE_STUDY_GROUP_WORDBOOK("update_studyGroupWordBook"),
    DELETE_STUDY_GROUP_WORDBOOK("delete_StudyGroupWordBook"),

    //Word
    ADD_WORD("add_word"),
    GET_WORD("get_word"),
    UPDATE_WORD("update_word"),
    DELETE_WORD("delete_word");

    private final String title;

    DomainLink(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static class User {
        public static Link create() {
            return linkTo(methodOn(UserController.class).create(null))
                    .withRel(CREATE_USER.toString());
        }

        public static Link get(Long userId) {
            return linkTo(methodOn(UserController.class).get(userId))
                    .withRel(GET_USER.toString());
        }

        public static Link update(Long userId) {
            return linkTo(methodOn(UserController.class).update(userId, null))
                    .withRel(UPDATE_USER.toString());
        }

        public static Link self(Long userId) {
            return linkTo(methodOn(UserController.class).get(userId))
                    .withSelfRel();
        }

        public static Link delete(Long userId)  {
            return linkTo(methodOn(UserController.class).delete())
                    .withRel(DELETE_USER.toString());
        }
    }

    public static class StudyGroup {
        public static Link create(Long userId) {
            return linkTo(methodOn(StudyGroupController.class).create(userId, null))
                    .withRel(CREATE_STUDY_GROUP.toString());
        }

        public static Link get(Long userId, Long studyGroupId) {
            return linkTo(methodOn(StudyGroupController.class).get(userId, studyGroupId))
                    .withRel(GET_STUDY_GROUP.toString());
        }

        public static Link update(Long userId, Long studyGroupId) {
            return linkTo(methodOn(StudyGroupController.class).update(userId, studyGroupId, null))
                    .withRel(UPDATE_STUDY_GROUP.toString());
        }

        public static Link delete(Long userId, Long studyGroupId) {
            return linkTo(methodOn(StudyGroupController.class).delete(userId, studyGroupId))
                    .withRel(DELETE_STUDY_GROUP.toString());
        }

        public static Link self(Long userId, Long studyGroupId) {
            return linkTo(methodOn(StudyGroupController.class).get(userId, studyGroupId))
                    .withSelfRel();
        }
    }

    public static class UserWordBook {
        public static Link create(Long userId) {
            return linkTo(methodOn(UserWordBookController.class).create(userId, null))
                    .withRel(CREATE_USER_WORDBOOK.toString());
        }

        public static Link get(Long userId, Long wordBookId) {
            return linkTo(methodOn(UserWordBookController.class).get(userId, wordBookId))
                    .withRel(GET_USER_WORDBOOK.toString());
        }

        public static Link update(Long userId, Long wordBookId) {
            return linkTo(methodOn(UserWordBookController.class).update(userId, wordBookId, null))
                    .withRel(UPDATE_USER_WORDBOOK.toString());
        }

        public static Link delete(Long userId, Long wordBookId) {
            return linkTo(methodOn(UserWordBookController.class).delete(userId, wordBookId))
                    .withRel(DELETE_USER_WORDBOOK.toString());
        }

        public static Link self(Long userId, Long wordBookId) {
            return linkTo(methodOn(UserWordBookController.class).get(userId, wordBookId))
                    .withSelfRel();
        }
    }

    public static class StudyGroupWordBook {
        public static Link create(Long userId, Long studyGroupId) {
            return linkTo(methodOn(StudyGroupWordBookController.class).create(userId, studyGroupId, null))
                    .withRel(CREATE_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link get(Long userId, Long studyGroupId, Long wordBookId) {
            return linkTo(methodOn(StudyGroupWordBookController.class).get(userId, studyGroupId, wordBookId))
                    .withRel(GET_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link update(Long userId, Long studyGroupId, Long wordBookId) {
            return linkTo(methodOn(StudyGroupWordBookController.class).update(userId, studyGroupId, wordBookId, null))
                    .withRel(UPDATE_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link delete(Long userId, Long studyGroupId, Long wordBookId) {
            return linkTo(methodOn(StudyGroupWordBookController.class).delete(userId, studyGroupId, wordBookId))
                    .withRel(DELETE_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link self(Long userId, Long studyGroupId, Long wordBookId) {
            return linkTo(methodOn(StudyGroupWordBookController.class).get(userId, studyGroupId, wordBookId))
                    .withSelfRel();
        }
    }

    public static class WordOfUserWordBook {
        public static Link add(Long userId, Long wordBookId) {
            return linkTo(methodOn(UserWordBookWordController.class).add(userId, wordBookId, null))
                    .withRel(ADD_WORD.toString());
        }

        public static Link update(Long userId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(UserWordBookWordController.class).update(userId, wordBookId, wordId, null))
                    .withRel(UPDATE_WORD.toString());
        }

        public static Link delete(Long userId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(UserWordBookWordController.class).delete(userId, wordBookId, wordId))
                    .withRel(DELETE_WORD.toString());
        }

        //TODO 링크 수정해야함
        public static Link self(Long userId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(UserWordBookWordController.class).delete(userId, wordBookId, wordId))
                    .withSelfRel();
        }
    }

    public static class WordOfStudyGroupWordBook {
        public static Link add(Long userId, Long studyGroupId, Long wordBookId) {
            return linkTo(methodOn(StudyGroupWordBookWordController.class).add(userId, studyGroupId, wordBookId, null))
                    .withRel(ADD_WORD.toString());
        }

        public static Link update(Long userId, Long studyGroupId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(StudyGroupWordBookWordController.class).update(userId, studyGroupId, wordBookId, wordId, null))
                    .withRel(UPDATE_WORD.toString());
        }

        public static Link delete(Long userId, Long studyGroupId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(StudyGroupWordBookWordController.class).delete(userId, studyGroupId, wordBookId, wordId))
                    .withRel(DELETE_WORD.toString());
        }

        public static Link self(Long userId, Long studyGroupId, Long wordBookId, Long wordId) {
            return linkTo(methodOn(StudyGroupWordBookWordController.class).delete(userId, studyGroupId, wordBookId, wordId))
                    .withSelfRel();
        }
    }
}
