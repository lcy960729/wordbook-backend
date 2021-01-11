package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.*;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.service.WordBookServiceFactory;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/wordBooks", produces = MediaTypes.HAL_JSON_VALUE)
public class WordBookController {
    private static final Logger logger = LoggerFactory.getLogger(WordBookController.class);

    private final WordBookServiceFactory wordBookServiceFactory;

    public WordBookController(WordBookServiceFactory wordBookServiceFactory) {
        this.wordBookServiceFactory = wordBookServiceFactory;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateWordBookDTO createGroupWordBookDTO){
        CreateWordBookService createWordBookService = wordBookServiceFactory.getCreateServiceImpl(createGroupWordBookDTO.getWordBookType());

        Long wordBookId = createWordBookService.create(createGroupWordBookDTO);

        URI createdUri = linkTo(WordBookController.class).slash(wordBookId).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable String id, @RequestParam("wordBookType") WordBookType wordBookType){
        GetWordBookService<WordBookDetailDTO, WordBook> getWordBookService = wordBookServiceFactory.getGetServiceImpl(wordBookType);
        WordBookDetailDTO groupWordBookDTO = getWordBookService.getDetailDTOById(Long.parseLong(id));

        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<Object> getWordBooks(){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UpdateWordBookDTO updateWordBookDTO){
        UpdateWordBookService<WordBookDetailDTO, UpdateWordBookDTO> updateWordBookService = wordBookServiceFactory.getUpdateServiceImpl(updateWordBookDTO.getWordBookType());
        WordBookDetailDTO wordBookDetailDTO =  updateWordBookService.update_name(Long.parseLong(id), updateWordBookDTO);

        return ResponseEntity.ok().body(wordBookDetailDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id, @RequestBody DeleteWordBookDTO deleteWordBookDTO){
        DeleteWordBookService deleteWordBookService = wordBookServiceFactory.getDeleteServiceImpl(deleteWordBookDTO.getWordBookType());
        deleteWordBookService.deleteById(Long.parseLong(id));

        return ResponseEntity.ok("deleted");
    }
}
