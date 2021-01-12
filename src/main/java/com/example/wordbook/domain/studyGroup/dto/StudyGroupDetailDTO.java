package com.example.wordbook.domain.studyGroup.dto;

import com.example.wordbook.domain.study.entity.Study;
import com.example.wordbook.domain.wordbook.entity.StudyGroupWordBook;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class StudyGroupDetailDTO {
    private Long id;
    private String name;
    private List<Study> userList;
    private List<StudyGroupWordBook> wordBookList;

    @Builder
    public StudyGroupDetailDTO(Long id, String name, List<Study> userList, List<StudyGroupWordBook> wordBookList) {
        this.id = id;
        this.name = name;
        this.userList = userList;
        this.wordBookList = wordBookList;
    }
}

