package com.example.wordbook.domain.studyGroup.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class StudyGroupUpdateControllerTest extends StudyGroupControllerTest {
//
//    private ResultActions requestUpdateUser(Long id, UpdateUserRequestDTO userUpdateDTO) throws Exception {
//        return mockMvc.perform(
//                put("/api/v1/users/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaTypes.HAL_JSON)
//                        .content(objectMapper.writeValueAsString(userUpdateDTO))
//        );
//    }
//
//    @Test
//    @DisplayName("정상적인 입력에 대해 유저 정보 업데이트 성공 테스트")
//    void update() throws Exception {
//        //given
//        Long userId = 0L;
//        String updateName = "updateName";
//
//        UpdateUserRequestDTO userUpdateDTO = UpdateUserRequestDTO.builder()
//                .name(updateName)
//                .build();
//
//        UserDetailResponseDTO userDetailResponseDTO = UserDetailResponseDTO.builder()
//                .id(userId)
//                .name(updateName)
//                .email("testEmail")
//                .userWordBookList(new ArrayList<>())
//                .build();
//
//        given(updateUserService.update(anyLong(), any())).willReturn(userDetailResponseDTO);
//
//        //when
//        ResultActions resultActions = requestUpdateUser(userId, userUpdateDTO);
//
//        //then
//        resultActions.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").exists())
//                .andExpect(jsonPath("name").exists())
//                .andExpect(jsonPath("email").exists())
//                .andExpect(jsonPath("_links.self").exists())
//                .andExpect(jsonPath("_links.update_user").exists())
//                .andExpect(jsonPath("_links.delete_user").exists())
//                .andExpect(jsonPath("_links.get_studyGroup").exists())
//                .andExpect(jsonPath("_links.get_wordBook").exists());
//    }
}