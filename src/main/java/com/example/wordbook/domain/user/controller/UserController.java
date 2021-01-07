package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.CreateUserDTO;
import com.example.wordbook.domain.user.dto.UpdateUserDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Validated
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {

    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private GetUserService getUserService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateUserDTO userCreateDTO) {
        Long id = createUserService.create(userCreateDTO);

        URI createdUri = linkTo(UserController.class).slash(id).toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(getUserService.getDetailDTOById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody UpdateUserDTO userUpdateDTO) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete() {

        return null;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> ConstraintViolationExceptionHandle(ConstraintViolationException constraintViolationException) {
        return ResponseEntity.unprocessableEntity().build();
    }
}
