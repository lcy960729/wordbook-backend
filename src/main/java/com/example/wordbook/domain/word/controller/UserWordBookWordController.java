package com.example.wordbook.domain.word.controller;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.service.AddWordService;
import com.example.wordbook.domain.word.service.DeleteWordService;
import com.example.wordbook.domain.word.service.UpdateWordService;
import com.example.wordbook.global.enums.DomainLink;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/wordbooks/{wordBookId}/words", produces = MediaTypes.HAL_JSON_VALUE)
public class UserWordBookWordController {

    private final AddWordService addWordService;
    private final UpdateWordService updateWordService;
    private final DeleteWordService deleteWordService;

    public UserWordBookWordController(AddWordService addWordService, UpdateWordService updateWordService, DeleteWordService deleteWordService) {
        this.addWordService = addWordService;
        this.updateWordService = updateWordService;
        this.deleteWordService = deleteWordService;
    }

    @PostMapping
    public ResponseEntity<Object> add(@PathVariable("userId") Long userId,
                                      @PathVariable("wordBookId") Long wordBookId,
                                      @RequestBody @Valid CreateWordDTO createWordDTO) {

        WordDetailDTO wordDetailDTO = addWordService.addAtUserWordBook(userId, wordBookId, createWordDTO);

        URI createdUri = DomainLink.WordOfUserWordBook.delete(userId, wordBookId, wordDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(wordDetailDTO);
    }

    @PutMapping(value = "/{wordId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId,
                                         @RequestBody UpdateWordDTO updateWordDTO) {

        WordDetailDTO wordDetailDTO = updateWordService.updateAtUserWordBook(userId, wordBookId, wordId, updateWordDTO);

        return ResponseEntity.ok().body(wordDetailDTO);
    }

    @DeleteMapping(value = "/{wordId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId) {

        deleteWordService.deleteAtUserWordBook(userId, wordBookId, wordId);

        return ResponseEntity.ok("deleted");
    }
}
