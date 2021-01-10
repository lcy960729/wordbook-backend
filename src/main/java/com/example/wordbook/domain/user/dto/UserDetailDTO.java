package com.example.wordbook.domain.user.dto;

import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDetailDTO {
    private Long id;
    private String name;
    private List<UserWordBookDTO> userWordBookList;

    @NoArgsConstructor
    @Data
    public static class UserWordBookDTO {
        private Long id;
        private String name;

        @Builder
        public UserWordBookDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Builder
    public UserDetailDTO(Long id, String name, List<WordBookResponseDTO.Detail> userWordBookList) {
        this.id = id;
        this.name = name;

        this.userWordBookList = new ArrayList<>();
        userWordBookList.forEach(userWordBook -> this.userWordBookList.add(new UserWordBookDTO(userWordBook.getId(), userWordBook.getName())));
    }
}
