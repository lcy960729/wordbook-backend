package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.request.CreateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.DeleteWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.request.UpdateWordBookDTO;
import com.example.wordbook.domain.wordbook.dto.response.WordBookDetailDTO;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.CreateUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.DeleteUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.GetUserWordBookService;
import com.example.wordbook.domain.wordbook.service.userwordbookImpl.UpdateUserWordBookService;
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
@RequestMapping(value = "/api/v1/users/{userId}/wordbooks", produces = MediaTypes.HAL_JSON_VALUE)
public class UserWordBookController {
    private static final Logger logger = LoggerFactory.getLogger(UserWordBookController.class);

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
    public ResponseEntity<Object> create(@PathVariable("userId") String userId, @RequestBody @Valid CreateWordBookDTO createGroupWordBookDTO) throws Exception {
        WordBookDetailDTO wordBookDetailDTO = createUserWordBookService.create(Long.parseLong(userId), createGroupWordBookDTO);

        URI createdUri = linkTo(methodOn(UserWordBookController.class).get(userId, wordBookDetailDTO.getId().toString())).toUri();

        return ResponseEntity.created(createdUri).body(wordBookDetailDTO);
    }

    @GetMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> get(@PathVariable("userId") String userId, @PathVariable("wordBookId") String wordBookId) throws Exception {
        WordBookDetailDTO groupWordBookDTO = getUserWordBookService.getDetailDTOByUserIdAndWordBookId(Long.parseLong(userId), Long.parseLong(wordBookId));

        return ResponseEntity.ok(groupWordBookDTO);
    }

    @PutMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> update(@PathVariable("userId") String userId, @PathVariable("wordBookId") String wordBookId, @RequestBody UpdateWordBookDTO updateWordBookDTO) throws Exception {
        WordBookDetailDTO wordBookDetailDTO = updateUserWordBookService.update_name(Long.parseLong(userId), Long.parseLong(wordBookId), updateWordBookDTO);

        return ResponseEntity.ok().body(wordBookDetailDTO);
    }

    @DeleteMapping(value = "/{wordBookId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") String userId, @PathVariable("wordBookId") String wordBookId, @RequestBody DeleteWordBookDTO deleteWordBookDTO) {
//        DeleteWordBookService deleteWordBookService = wordBookServiceFactory.getDeleteServiceImpl(wordBookType);
//        deleteWordBookService.deleteById(Long.parseLong(userId), );

        return ResponseEntity.ok("deleted");
    }
}
