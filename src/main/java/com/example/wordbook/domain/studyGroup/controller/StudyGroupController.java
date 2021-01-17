package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.request.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.dto.response.StudyGroupDetailDTO;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.DeleteStudyGroupService;
import com.example.wordbook.domain.studyGroup.service.GetStudyGroupService;
import com.example.wordbook.domain.user.dto.request.UpdateUserDTO;
import com.example.wordbook.global.enums.DomainLink;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/study-groups", produces = MediaTypes.HAL_JSON_VALUE)
public class StudyGroupController {

    private final CreateStudyGroupService createStudyGroupService;
    private final GetStudyGroupService getStudyGroupService;
    private final DeleteStudyGroupService deleteStudyGroupService;

    public StudyGroupController(CreateStudyGroupService createStudyGroupService, GetStudyGroupService getStudyGroupService, DeleteStudyGroupService deleteStudyGroupService) {
        this.createStudyGroupService = createStudyGroupService;
        this.getStudyGroupService = getStudyGroupService;
        this.deleteStudyGroupService = deleteStudyGroupService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable("userId") Long userId,
                                         @RequestBody @Valid CreateStudyGroupDTO createStudyGroupDTO) {
        StudyGroupDetailDTO studyGroupDetailDTO = createStudyGroupService.create(userId, createStudyGroupDTO);

        URI createdUri = DomainLink.StudyGroup.get(userId, studyGroupDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(studyGroupDetailDTO);
    }

    @GetMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> get(@PathVariable("userId") Long userId,
                                      @PathVariable("studyGroupId") Long studyGroupId) {
        StudyGroupDetailDTO studyGroupAdminResponseDTO = getStudyGroupService.getDetailDTOByUserIdAndStudyGroupId(userId, studyGroupId);

        return ResponseEntity.ok(studyGroupAdminResponseDTO);
    }

    @PutMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @RequestBody @Valid UpdateUserDTO userUpdateDTO) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{studyGroupId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId) {

        deleteStudyGroupService.delete(userId, studyGroupId);
        
        return null;
    }
}
