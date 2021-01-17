package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.groupwordbook.CreateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbook.DeleteStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbook.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbook.UpdateStudyGroupWordBookService;
import com.example.wordbook.global.enums.DomainLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks", produces = MediaTypes.HAL_JSON_VALUE)
public class StudyGroupWordBookController {

    private final CreateStudyGroupWordBookService createStudyGroupWordBookService;
    private final GetStudyGroupWordBookService getStudyGroupWordBookService;
    private final UpdateStudyGroupWordBookService updateStudyGroupWordBookService;
    private final DeleteStudyGroupWordBookService deleteStudyGroupWordBookService;

    public StudyGroupWordBookController(CreateStudyGroupWordBookService createStudyGroupWordBookService,
                                        GetStudyGroupWordBookService getStudyGroupWordBookService,
                                        UpdateStudyGroupWordBookService updateStudyGroupWordBookService,
                                        DeleteStudyGroupWordBookService deleteStudyGroupWordBookService) {

        this.createStudyGroupWordBookService = createStudyGroupWordBookService;
        this.getStudyGroupWordBookService = getStudyGroupWordBookService;
        this.updateStudyGroupWordBookService = updateStudyGroupWordBookService;
        this.deleteStudyGroupWordBookService = deleteStudyGroupWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @RequestBody @Valid CreateWordBookDTO createGroupWordBookDTO) {
        WordBookDetailDTO wordBookDetailDTO = createStudyGroupWordBookService.create(
                userId,
                studyGroupId,
                createGroupWordBookDTO);

        URI createdUri = DomainLink.StudyGroupWordBook.get(userId, studyGroupId, wordBookDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(wordBookDetailDTO);
    }

    @GetMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> get(@PathVariable("userId") Long userId,
                                      @PathVariable("studyGroupId") Long studyGroupId,
                                      @PathVariable("wordBookId") Long wordBookId) {
        WordBookDetailDTO groupWordBookDTO = getStudyGroupWordBookService.getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(
                userId,
                studyGroupId,
                wordBookId);

        return ResponseEntity.ok(groupWordBookDTO);
    }

    @PutMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @RequestBody @Valid UpdateWordBookDTO updateWordBookDTO) {
        WordBookDetailDTO wordBookDetailDTO = updateStudyGroupWordBookService.update_name(
                userId,
                studyGroupId,
                wordBookId,
                updateWordBookDTO);

        return ResponseEntity.ok(wordBookDetailDTO);
    }

    @DeleteMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @PathVariable("wordBookId") Long wordBookId) {

        deleteStudyGroupWordBookService.delete(userId, studyGroupId, wordBookId);

        return ResponseEntity.ok("deleted");
    }
}
