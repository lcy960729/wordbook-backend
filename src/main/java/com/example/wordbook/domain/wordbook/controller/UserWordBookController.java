package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.userwordbook.CreateUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.DeleteUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.GetUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbook.UpdateUserWordBookService;
import com.example.wordbook.global.enums.DomainLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/wordbooks", produces = MediaTypes.HAL_JSON_VALUE)
public class UserWordBookController {

    private final CreateUserWordBookService createUserWordBookService;
    private final GetUserWordBookService getUserWordBookService;
    private final UpdateUserWordBookService updateUserWordBookService;
    private final DeleteUserWordBookService deleteUserWordBookService;

    public UserWordBookController(CreateUserWordBookService createUserWordBookService, GetUserWordBookService getUserWordBookService, DeleteUserWordBookService deleteUserWordBookService, UpdateUserWordBookService updateUserWordBookService) {
        this.createUserWordBookService = createUserWordBookService;
        this.getUserWordBookService = getUserWordBookService;
        this.deleteUserWordBookService = deleteUserWordBookService;
        this.updateUserWordBookService = updateUserWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable("userId") Long userId,
                                         @RequestBody @Valid CreateWordBookDTO createGroupWordBookDTO) {
        WordBookDetailDTO wordBookDetailDTO = createUserWordBookService.create(userId, createGroupWordBookDTO);

        URI createdUri = DomainLink.UserWordBook.get(userId, wordBookDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(wordBookDetailDTO);
    }

    @GetMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> get(@PathVariable("userId") Long userId,
                                      @PathVariable("wordBookId") Long wordBookId)   {
        WordBookDetailDTO groupWordBookDTO = getUserWordBookService.getDetailDTOByUserIdAndWordBookId(userId, wordBookId);

        return ResponseEntity.ok(groupWordBookDTO);
    }

    @PutMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId,
                                         @RequestBody UpdateWordBookDTO updateWordBookDTO)   {
        WordBookDetailDTO wordBookDetailDTO = updateUserWordBookService.update_name(userId, wordBookId, updateWordBookDTO);

        return ResponseEntity.ok().body(wordBookDetailDTO);
    }

    @DeleteMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId,
                                         @PathVariable("wordBookId") Long wordBookId) {

        deleteUserWordBookService.deleteById(userId, wordBookId);

        return ResponseEntity.ok("deleted");
    }
}
