package com.example.wordbook.domain.word.controller;

import com.example.wordbook.domain.word.dto.request.CreateWordDTO;
import com.example.wordbook.domain.word.dto.request.UpdateWordDTO;
import com.example.wordbook.domain.word.dto.response.WordDetailDTO;
import com.example.wordbook.domain.word.service.userwordbook.AddWordOfUserWordBookService;
import com.example.wordbook.domain.word.service.userwordbook.DeleteWordOfUserWordBookService;
import com.example.wordbook.domain.word.service.userwordbook.UpdateWordOfUserWordBookService;
import com.example.wordbook.global.enums.DomainLink;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/wordbooks/{wordBookId}/words", produces = MediaTypes.HAL_JSON_VALUE)
public class WordOfUserWordBookController {

    private final AddWordOfUserWordBookService addWordOfUserWordBookService;
    private final UpdateWordOfUserWordBookService updateWordOfUserWordBookService;
    private final DeleteWordOfUserWordBookService deleteWordOfUserWordBookService;

    public WordOfUserWordBookController(AddWordOfUserWordBookService addWordOfUserWordBookService, UpdateWordOfUserWordBookService updateWordOfUserWordBookService, DeleteWordOfUserWordBookService deleteWordOfUserWordBookService) {
        this.addWordOfUserWordBookService = addWordOfUserWordBookService;
        this.updateWordOfUserWordBookService = updateWordOfUserWordBookService;
        this.deleteWordOfUserWordBookService = deleteWordOfUserWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> add(@PathVariable("userId") Long userId,
                                      @PathVariable("wordBookId") Long wordBookId,
                                      @RequestBody @Valid CreateWordDTO createWordDTO) {

        WordDetailDTO wordDetailDTO = addWordOfUserWordBookService.add(userId, wordBookId, createWordDTO);

        URI createdUri = DomainLink.WordOfUserWordBook.delete(userId, wordBookId, wordDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(wordDetailDTO);
    }

    @PutMapping(value = "/{wordId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId,
                                         @RequestBody UpdateWordDTO updateWordDTO) {

        WordDetailDTO wordDetailDTO = updateWordOfUserWordBookService.update(userId, wordBookId, wordId, updateWordDTO);

        return ResponseEntity.ok().body(wordDetailDTO);
    }

    @DeleteMapping(value = "/{wordId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @PathVariable("wordId") Long wordId) {

        deleteWordOfUserWordBookService.delete(userId, wordBookId, wordId);

        return ResponseEntity.ok("deleted");
    }
}
