package com.example.wordbook.domain.word.controller;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.service.studygroupwordbook.AddWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.DeleteWordOfStudyGroupWordBookService;
import com.example.wordbook.domain.word.service.studygroupwordbook.UpdateWordOfStudyGroupWordBookService;

import com.example.wordbook.global.enums.DomainLink;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/study-groups/{studyGroupId}/wordbooks/{wordBookId}/words", produces = MediaTypes.HAL_JSON_VALUE)
public class WordOfStudyGroupWordBookController {

    private final AddWordOfStudyGroupWordBookService addWordOfStudyGroupWordBookService;
    private final UpdateWordOfStudyGroupWordBookService updateWordOfStudyGroupWordBookService;
    private final DeleteWordOfStudyGroupWordBookService deleteWordOfStudyGroupWordBookService;

    public WordOfStudyGroupWordBookController(AddWordOfStudyGroupWordBookService addWordOfStudyGroupWordBookService, UpdateWordOfStudyGroupWordBookService updateWordOfStudyGroupWordBookService, DeleteWordOfStudyGroupWordBookService deleteWordOfStudyGroupWordBookService) {
        this.addWordOfStudyGroupWordBookService = addWordOfStudyGroupWordBookService;
        this.updateWordOfStudyGroupWordBookService = updateWordOfStudyGroupWordBookService;
        this.deleteWordOfStudyGroupWordBookService = deleteWordOfStudyGroupWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> add(@PathVariable("userId") Long userId,
                                      @PathVariable("studyGroupId") Long studyGroupId,
                                      @PathVariable("wordBookId") Long wordBookId,
                                      @RequestBody @Valid CreateWordDTO createWordDTO) {

        WordDetailDTO wordDetailDTO = addWordOfStudyGroupWordBookService.add(userId, studyGroupId, wordBookId, createWordDTO);

        URI createdUri = DomainLink.WordOfStudyGroupWordBook.self(userId, studyGroupId, wordBookId, wordDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(wordDetailDTO);
    }

    @PutMapping(value = "/{wordId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId,
                                         @RequestBody UpdateWordDTO updateWordDTO) {

        WordDetailDTO wordDetailDTO = updateWordOfStudyGroupWordBookService.update(userId, studyGroupId, wordBookId, wordId, updateWordDTO);

        return ResponseEntity.ok().body(wordDetailDTO);
    }

    @DeleteMapping(value = "/{wordId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("studyGroupId") Long studyGroupId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId) {

        deleteWordOfStudyGroupWordBookService.delete(userId, studyGroupId, wordBookId, wordId);

        return ResponseEntity.ok("deleted");
    }
}
