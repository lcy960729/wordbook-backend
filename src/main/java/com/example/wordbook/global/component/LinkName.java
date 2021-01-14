package com.example.wordbook.global.component;

public enum LinkName {
    SELF("self"),
    CREATE_USER("create_user"),
    GET_USER("get_user"),
    UPDATE_USER("update_user"),
    DELETE_USER("delete_user"),
    CREATE_STUDY_GROUP("create_user"),
    GET_STUDY_GROUP("create_user"),
    UPDATE_STUDY_GROUP("create_user"),
    DELETE_STUDY_GROUP("create_user"),
    CREATE_USER_WORDBOOK("create_userWordBook"),
    GET_USER_WORDBOOK("get_userWordBook"),
    UPDATE_USER_WORDBOOK("update_userWordBook"),
    DELETE_USER_WORDBOOK("delete_userWordBook"),
    CREATE_STUDY_GROUP_WORDBOOK("create_studyGroupWordBook"),
    GET_STUDY_GROUP_WORDBOOK("get_studyGroupWordBook"),
    UPDATE_STUDY_GROUP_WORDBOOK("update_studyGroupWordBook"),
    DELETE_STUDY_GROUP_WORDBOOK("delete_StudyGroupWordBook");


    private final String title;
    LinkName(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
