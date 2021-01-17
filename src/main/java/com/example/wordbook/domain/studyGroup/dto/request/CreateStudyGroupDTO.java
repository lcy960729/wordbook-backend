package com.example.wordbook.domain.studyGroup.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CreateStudyGroupDTO {

    @NotBlank
    private String name;

    @Builder
    public CreateStudyGroupDTO(String name) {
        this.name = name;
    }
}
