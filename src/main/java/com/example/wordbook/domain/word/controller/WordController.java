package com.example.wordbook.domain.word.controller;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.word.dto.WordRequestDTO;
import com.example.wordbook.global.exception.BusinessException;
import com.example.wordbook.domain.word.service.CreateWordService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/words", produces = MediaTypes.HAL_JSON_VALUE)
public class WordController {

    private final CreateWordService createWordService;

    public WordController(CreateWordService createWordService) {
        this.createWordService = createWordService;
    }

    @PostMapping
    public ResponseEntity<Object> createWord(@RequestBody @Valid WordRequestDTO.Create createWordDTO) throws ChangeSetPersister.NotFoundException {

        Long wordId = createWordService.create(createWordDTO);

        URI createdUri = linkTo(WordController.class).slash(wordId).toUri();

        return ResponseEntity.created(createdUri).body(createWordDTO);
    }

//    @GetMapping
//    public ResponseEntity<Object> getWordBooks(){
//        return null;
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Object> updateWord(@PathVariable String id, @RequestBody GroupWordBookRequestDTO.Update updateGroupWordBookDTO){
//        GroupWordBookResponseDTO.Detail groupWordBookDTO =  updateGroupWordBookService.update_name(Long.parseLong(id), updateGroupWordBookDTO);
//        return ResponseEntity.ok().body(groupWordBookDTO);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Object> deleteWord(@PathVariable String id){
//        deleteGroupWordBookService.deleteById(Long.parseLong(id));
//        return ResponseEntity.ok("deleted");
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> validationExceptionHandle(BusinessException e){

        return ResponseEntity.badRequest().build();
    }
}
