package com.example.wordbook.domain.studyGroup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateStudyGroupDTO {
    private String name;
    private Long groupOwnerId;

    @Builder
    public CreateStudyGroupDTO(String name, Long groupOwnerId) {
        this.name = name;
        this.groupOwnerId = groupOwnerId;
    }

    //    public static builder builder = new builder();
//
//    public static class builder{
//        private String name;
//        private Long groupOwnerId;
//
//        public builder name(String name){
//            this.name = name;
//            return this;
//        }
//
//        public builder groupOwnerId(Long groupOwnerId) {
//            this.groupOwnerId = groupOwnerId;
//            return this;
//        }
//
//        public CreateGroupDTO build(){
//            return new CreateGroupDTO(this);
//        }
//    }
//
//    public CreateGroupDTO(builder builder) {
//        this.name = builder.name;
//        this.groupOwnerId = builder.groupOwnerId;
//    }
}
