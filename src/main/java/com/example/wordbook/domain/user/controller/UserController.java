package com.example.wordbook.domain.user.controller;

import com.example.wordbook.domain.user.dto.request.CreateUserDTO;
import com.example.wordbook.domain.user.dto.request.UpdateUserDTO;
import com.example.wordbook.domain.user.dto.response.UserDetailDTO;
import com.example.wordbook.domain.user.service.CreateUserService;
import com.example.wordbook.domain.user.service.GetUserService;
import com.example.wordbook.domain.user.service.UpdateUserService;
import com.example.wordbook.global.enums.DomainLink;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Validated
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {

    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService, GetUserService getUserService, UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserDetailDTO userDetailDTO = createUserService.create(createUserDTO);

        URI createdUri = DomainLink.User.get(userDetailDTO.getId()).toUri();

        return ResponseEntity.created(createdUri).body(userDetailDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(getUserService.getDetailDTOById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,
                                         @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(updateUserService.update(id, updateUserDTO));
    }

    @DeleteMapping
    public ResponseEntity<Object> delete() {
        return null;
    }
}
