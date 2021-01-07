package com.example.wordbook.domain.groupwordbook.controller;

import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookRequestDTO;
import com.example.wordbook.domain.groupwordbook.service.CreateGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.GetGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.service.UpdateGroupWordBookService;
import com.example.wordbook.domain.groupwordbook.dto.GroupWordBookResponseDTO;
import com.example.wordbook.domain.groupwordbook.service.DeleteGroupWordBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// 에러코드 반환시
// 서비스에서 발생한 에러코드를 어떻게 처리할건지
// 유효성 검증을 어떻게 잘 처리할까?

@RestController
@RequestMapping(value = "/api/v1/groupWordBooks", produces = MediaTypes.HAL_JSON_VALUE)
public class GroupWordBookController {
    private static final Logger logger = LoggerFactory.getLogger(GroupWordBookController.class);

    private final CreateGroupWordBookService createGroupWordBookService;
    private final GetGroupWordBookService getGroupWordBookService;
    private final DeleteGroupWordBookService deleteGroupWordBookService;
    private final UpdateGroupWordBookService updateGroupWordBookService;

    public GroupWordBookController(CreateGroupWordBookService createGroupWordBookService, GetGroupWordBookService getGroupWordBookService, DeleteGroupWordBookService deleteGroupWordBookService, UpdateGroupWordBookService updateGroupWordBookService) {
        this.createGroupWordBookService = createGroupWordBookService;
        this.getGroupWordBookService = getGroupWordBookService;
        this.deleteGroupWordBookService = deleteGroupWordBookService;
        this.updateGroupWordBookService = updateGroupWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> createGroupWordBook(@RequestBody @Valid GroupWordBookRequestDTO.Create createGroupWordBookDTO){
        Long wordBookId = createGroupWordBookService.create(createGroupWordBookDTO);

        URI createdUri = linkTo(GroupWordBookController.class).slash(wordBookId).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getWordBook(@PathVariable String id){
        GroupWordBookResponseDTO.Detail groupWordBookDTO = getGroupWordBookService.getDetailDTOById(Long.parseLong(id));

        return ResponseEntity.ok(groupWordBookDTO);
    }

    @GetMapping
    public ResponseEntity<Object> getWordBooks(){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateWordBook(@PathVariable String id, @RequestBody GroupWordBookRequestDTO.Update updateGroupWordBookDTO){
        GroupWordBookResponseDTO.Detail groupWordBookDTO =  updateGroupWordBookService.update_name(Long.parseLong(id), updateGroupWordBookDTO);
        return ResponseEntity.ok().body(groupWordBookDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteWordBook(@PathVariable String id){
        deleteGroupWordBookService.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("deleted");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
        for(ObjectError objectError : e.getBindingResult().getAllErrors()) {
            logger.error(objectError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationExceptionHandle(ConstraintViolationException e){

        return ResponseEntity.badRequest().body(e);
    }
}
