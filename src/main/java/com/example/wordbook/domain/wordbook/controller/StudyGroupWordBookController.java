package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.CreateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.DeleteStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.GetStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.groupwordbookImpl.UpdateStudyGroupWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks", produces = MediaTypes.HAL_JSON_VALUE)
public class StudyGroupWordBookController {
    private static final Logger logger = LoggerFactory.getLogger(StudyGroupWordBookController.class);

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
    public ResponseEntity<Object> create(@PathVariable("userId") String userId,
                                         @PathVariable("studyGroupId") String studyGroupId,
                                         @RequestBody @Valid CreateWordBookDTO createGroupWordBookDTO) throws Exception {
        WordBookDetailDTO wordBookDetailDTO = createStudyGroupWordBookService.create(
                Long.parseLong(userId),
                Long.parseLong(studyGroupId),
                createGroupWordBookDTO);

        URI createdUri = linkTo(methodOn(StudyGroupWordBookController.class).get(userId,studyGroupId,wordBookDetailDTO.getId().toString())).toUri();

        return ResponseEntity.created(createdUri).body(wordBookDetailDTO);
    }

    @GetMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> get(@PathVariable("userId") String userId,
                                      @PathVariable("studyGroupId") String studyGroupId,
                                      @PathVariable("wordBookId") String wordBookId) throws Exception {
        WordBookDetailDTO groupWordBookDTO = getStudyGroupWordBookService.getDetailDTOByUserIdAndStudyGroupIdAndWordBookId(
                Long.parseLong(userId),
                Long.parseLong(studyGroupId),
                Long.parseLong(wordBookId));

        return ResponseEntity.ok(groupWordBookDTO);
    }

    @PutMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> update(@PathVariable("userId") String userId,
                                         @PathVariable("studyGroupId") String studyGroupId,
                                         @PathVariable("wordBookId") String wordBookId,
                                         @RequestBody UpdateWordBookDTO updateWordBookDTO) throws Exception {
        WordBookDetailDTO wordBookDetailDTO = updateStudyGroupWordBookService.update_name(
                Long.parseLong(userId),
                Long.parseLong(studyGroupId),
                Long.parseLong(wordBookId),
                updateWordBookDTO);

        return ResponseEntity.ok(wordBookDetailDTO);
    }

    @DeleteMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") String userId,
                                         @PathVariable("studyGroupId") String studyGroupId,
                                         @PathVariable("wordBookId") String wordBookId) {
//        DeleteWordBookService deleteWordBookService = wordBookServiceFactory.getDeleteServiceImpl(deleteWordBookDTO.getWordBookType());
//        deleteWordBookService.deleteById(Long.parseLong(id));

        return ResponseEntity.ok("deleted");
    }
}
