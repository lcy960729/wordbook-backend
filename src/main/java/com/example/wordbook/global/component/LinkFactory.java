package com.example.wordbook.global.component;

import com.example.wordbook.domain.studyGroup.controller.StudyGroupController;
import com.example.wordbook.domain.user.controller.UserController;
import com.example.wordbook.domain.wordbook.controller.StudyGroupWordBookController;
import com.example.wordbook.domain.wordbook.controller.UserWordBookController;
import org.springframework.hateoas.Link;

import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LinkFactory {

    public static class User {
        public static Link create() throws Exception {
            return linkTo(methodOn(UserController.class).create(null))
                    .withRel(LinkName.CREATE_USER.toString());
        }

        public static Link get(Long userId) throws Exception {
            return linkTo(methodOn(UserController.class).get(userId))
                    .withRel(LinkName.GET_USER.toString());
        }

        public static Link update(Long userId) throws Exception {
            return linkTo(methodOn(UserController.class).update(userId, null))
                    .withRel(LinkName.UPDATE_USER.toString());
        }

        public static Link self(Long userId) throws Exception {
            return linkTo(methodOn(UserController.class).get(userId))
                    .withSelfRel();
        }

//        public static Link DeleteUserURL(Long userId) throws Exception {
//            return linkTo(methodOn(UserController.class).delete());
//        }
    }

    public static class StudyGroup {
        public static Link create(Long userId) throws Exception {
            return linkTo(methodOn(StudyGroupController.class).create(
                    userId.toString(),
                    null))
                    .withRel(LinkName.CREATE_STUDY_GROUP.toString());
        }

        public static Link get(Long userId, Long groupId) throws Exception {
            return linkTo(methodOn(StudyGroupController.class).get(
                    userId.toString(),
                    groupId.toString()))
                    .withRel(LinkName.GET_STUDY_GROUP.toString());
        }

        public static Link update(Long userId, Long groupId) throws Exception {
            return linkTo(methodOn(StudyGroupController.class).update(
                    userId.toString(),
                    groupId.toString(),
                    null))
                    .withRel(LinkName.DELETE_STUDY_GROUP.toString());
        }

        public static Link self(Long userId, Long groupId) throws Exception {
            return linkTo(methodOn(StudyGroupController.class).get(
                    userId.toString(),
                    groupId.toString()))
                    .withSelfRel();
        }

//    public static Link DeleteUserURL(Long userId) throws Exception {
//        return linkTo(methodOn(StudyGroup.class).delete());
//    }
    }

    public static class UserWordBook {
        public static Link create(Long userId) throws Exception {
            return linkTo(methodOn(UserWordBookController.class).create(
                    userId.toString(),
                    null))
                    .withRel(LinkName.CREATE_USER_WORDBOOK.toString());
        }

        public static Link get(Long userId, Long wordBookId) throws Exception {
            return linkTo(methodOn(UserWordBookController.class).get(
                    userId.toString(),
                    wordBookId.toString()))
                    .withRel(LinkName.GET_USER_WORDBOOK.toString());
        }

        public static Link update(Long userId, Long wordBookId) throws Exception {
            return linkTo(methodOn(UserWordBookController.class).update(
                    userId.toString(),
                    wordBookId.toString(),
                    null))
                    .withRel(LinkName.UPDATE_USER_WORDBOOK.toString());
        }

        public static Link self(Long userId, Long wordBookId) throws Exception {
            return linkTo(methodOn(UserWordBookController.class).get(
                    userId.toString(),
                    wordBookId.toString()))
                    .withSelfRel();
        }


//    public static Link DeleteUserURL(Long userId) throws Exception {
//        return linkTo(methodOn(StudyGroup.class).delete());
//    }
    }

    public static class StudyGroupWordBook {
        public static Link create(Long userId, Long groupId) throws Exception {
            return linkTo(methodOn(StudyGroupWordBookController.class).create(
                    userId.toString(),
                    groupId.toString(),
                    null))
                    .withRel(LinkName.CREATE_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link get(Long userId, Long groupId, Long wordBookId) throws Exception {
            return linkTo(methodOn(StudyGroupWordBookController.class).get(
                    userId.toString(),
                    groupId.toString(),
                    wordBookId.toString()))
                    .withRel(LinkName.GET_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link update(Long userId, Long groupId, Long wordBookId) throws Exception {
            return linkTo(methodOn(StudyGroupWordBookController.class).update(
                    userId.toString(),
                    groupId.toString(),
                    wordBookId.toString(),
                    null))
                    .withRel(LinkName.UPDATE_STUDY_GROUP_WORDBOOK.toString());
        }

        public static Link self(Long userId, Long groupId, Long wordBookId) throws Exception {
            return linkTo(methodOn(StudyGroupWordBookController.class).get(
                    userId.toString(),
                    groupId.toString(),
                    wordBookId.toString()))
                    .withSelfRel();
        }

//    public static Link DeleteUserURL(Long userId) throws Exception {
//        return linkTo(methodOn(StudyGroup.class).delete());
//    }
    }
}
