package com.example.wordbook.domain.studyGroup.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UpdateStudyGroupDTO {
    @NotBlank
    private String name;

    public UpdateStudyGroupDTO(@NotBlank String name) {
        this.name = name;
    }
}
