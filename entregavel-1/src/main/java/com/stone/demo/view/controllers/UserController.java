package com.stone.demo.view.controllers;


import com.stone.demo.domain.services.UserService;
import com.stone.demo.view.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin("http://localhost")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest request,
                                             @RequestHeader(required = false) String requestId) {
        return userService.execute(request, requestId);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestHeader(required = false) String requestId){
        return userService.execute(requestId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUser(String cpf,
                                           @RequestHeader(required = false) String requestId){
        return userService.execute(cpf, requestId);
    }
}
