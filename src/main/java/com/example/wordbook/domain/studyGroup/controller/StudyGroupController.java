package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupRequestDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailResponseDTO;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.domain.user.dto.request.UpdateUserRequestDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
@RequestMapping(value = "/api/v1/users/{userId}/study-groups", produces = MediaTypes.HAL_JSON_VALUE)
public class StudyGroupController {

    private final CreateStudyGroupService createStudyGroupService;
    private final GetStudyGroupService getStudyGroupService;

    public StudyGroupController(CreateStudyGroupService createStudyGroupService, GetStudyGroupService getStudyGroupService) {
        this.createStudyGroupService = createStudyGroupService;
        this.getStudyGroupService = getStudyGroupService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable("userId") String userId, @RequestBody @Valid CreateStudyGroupRequestDTO createStudyGroupRequestDTO) throws Exception {

        StudyGroupDetailResponseDTO studyGroupDetailResponseDTO = createStudyGroupService.create(Long.parseLong(userId), createStudyGroupRequestDTO);

        URI createdUri = linkTo(methodOn(StudyGroupController.class).create(userId, null))
                .slash(studyGroupDetailResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(createdUri).body(studyGroupDetailResponseDTO);
    }

    @GetMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> get(@PathVariable("userId") String userId, @PathVariable("studyGroupId") String groupId) throws Exception {
        StudyGroupDetailResponseDTO studyGroupAdminResponseDTO = getStudyGroupService.getDetailDTOById(Long.parseLong(userId), Long.parseLong(groupId));

        return ResponseEntity.ok(studyGroupAdminResponseDTO);
    }

    @PutMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> update(@PathVariable("userId") String userId,
                                         @PathVariable("studyGroupId") String groupId,
                                         @RequestBody UpdateUserRequestDTO userUpdateDTO) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") String userId, @PathVariable("studyGroupId") String groupId) {

        return null;
    }
}
