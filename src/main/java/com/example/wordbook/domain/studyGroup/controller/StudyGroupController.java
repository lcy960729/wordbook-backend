package com.example.wordbook.domain.studyGroup.controller;

import com.example.wordbook.domain.studyGroup.dto.CreateStudyGroupDTO;
import com.example.wordbook.domain.studyGroup.service.CreateStudyGroupService;
import com.example.wordbook.domain.user.dto.UpdateUserDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Validated
@RestController
@RequestMapping(value = "/api/v1/groups", produces = MediaTypes.HAL_JSON_VALUE)
public class StudyGroupController {

    private final CreateStudyGroupService createStudyGroupService;

    public StudyGroupController(CreateStudyGroupService createStudyGroupService) {
        this.createStudyGroupService = createStudyGroupService;
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateStudyGroupDTO createStudyGroupDTO) throws Exception {
        Long id = createStudyGroupService.create(createStudyGroupDTO).getId();

        URI createdUri = linkTo(StudyGroupController.class).slash(id).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody UpdateUserDTO userUpdateDTO) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete() {

        return null;
    }
}
