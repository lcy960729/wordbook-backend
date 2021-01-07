package com.example.wordbook.domain.userwordbook.controller;

import com.example.wordbook.domain.userwordbook.dto.UserWordBookRequestDTO;
import com.example.wordbook.domain.userwordbook.dto.UserWordBookResponseDTO;
import com.example.wordbook.domain.userwordbook.service.CreateUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.DeleteUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.GetUserWordBookService;
import com.example.wordbook.domain.userwordbook.service.UpdateUserWordBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
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
//

@RestController
@RequestMapping(value = "/api/v1/userWordBooks", produces = MediaTypes.HAL_JSON_VALUE)
public class UserWordBookController {
    private static final Logger logger = LoggerFactory.getLogger(UserWordBookController.class);

    private final CreateUserWordBookService createUserWordBookService;
    private final GetUserWordBookService getUserWordBookService;
    private final DeleteUserWordBookService deleteUserWordBookService;
    private final UpdateUserWordBookService updateUserWordBookService;

    public UserWordBookController(CreateUserWordBookService createUserWordBookService, GetUserWordBookService getUserWordBookService, DeleteUserWordBookService deleteUserWordBookService, UpdateUserWordBookService updateUserWordBookService) {
        this.createUserWordBookService = createUserWordBookService;
        this.getUserWordBookService = getUserWordBookService;
        this.deleteUserWordBookService = deleteUserWordBookService;
        this.updateUserWordBookService = updateUserWordBookService;
    }

    @PostMapping
    public ResponseEntity<Object> createUserWordBook(@RequestBody @Valid UserWordBookRequestDTO.Create createUserWordBookDTO){
        Long wordBookId = createUserWordBookService.create(createUserWordBookDTO);

        URI createdUri = linkTo(UserWordBookController.class).slash(wordBookId).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getWordBook(@PathVariable String id){
        UserWordBookResponseDTO.Detail userWordBookDTO = getUserWordBookService.getDetailDTOById(Long.parseLong(id));

        return ResponseEntity.ok(userWordBookDTO);
    }

    @GetMapping
    public ResponseEntity<Object> getWordBooks(){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateWordBook(@PathVariable String id, @RequestBody UserWordBookRequestDTO.Update updateUserWordBookDTO){
        UserWordBookResponseDTO.Detail userWordBookDTO =  updateUserWordBookService.update_name(Long.parseLong(id), updateUserWordBookDTO);
        return ResponseEntity.ok().body(userWordBookDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteWordBook(@PathVariable String id){
        deleteUserWordBookService.deleteById(Long.parseLong(id));
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
