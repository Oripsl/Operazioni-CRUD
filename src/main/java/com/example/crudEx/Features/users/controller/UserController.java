package com.example.crudEx.Features.users.controller;

import com.example.crudEx.Features.users.DTO.CreateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest createUserRequest) {
        UserDTO result = userService.createUser(createUserRequest);
        if(result == null) {
            return  ResponseEntity.status(420).body("Si scem");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
