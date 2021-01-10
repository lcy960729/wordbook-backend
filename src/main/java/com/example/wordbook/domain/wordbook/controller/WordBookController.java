package com.example.wordbook.domain.wordbook.controller;

import com.example.wordbook.domain.wordbook.dto.WordBookRequestDTO;
import com.example.wordbook.domain.wordbook.dto.WordBookResponseDTO;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import com.example.wordbook.domain.wordbook.enums.WordBookType;
import com.example.wordbook.domain.wordbook.service.WordBookServiceFactory;
import com.example.wordbook.domain.wordbook.service.wordbook.CreateWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.DeleteWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.GetWordBookService;
import com.example.wordbook.domain.wordbook.service.wordbook.UpdateWordBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private WordBookServiceFactory wordBookServiceFactory;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid WordBookRequestDTO.Create createGroupWordBookDTO){
        CreateWordBookService createWordBookService = wordBookServiceFactory.getCreateServiceImpl(createGroupWordBookDTO.getWordBookType());

        Long wordBookId = createWordBookService.create(createGroupWordBookDTO);

        URI createdUri = linkTo(WordBookController.class).slash(wordBookId).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@RequestParam("wordBookType") WordBookType wordBookType, @PathVariable String id){
//        GetWordBookService<WordBookResponseDTO.Detail, WordBook> getWordBookService = wordBookServiceFactory.getGetServiceImpl(wordBookType);
//        WordBookResponseDTO.Detail groupWordBookDTO = getWordBookService.getDetailDTOById(Long.parseLong(id));

        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<Object> getWordBooks(){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody WordBookRequestDTO.Update updateGroupWordBookDTO){
//        WordBookResponseDTO.Detail groupWordBookDTO =  updateWordBookService.update_name(Long.parseLong(id), updateGroupWordBookDTO);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
//        deleteWordBookService.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("deleted");
    }
}
