package com.example.crudEx.Features.users.controller;

import com.example.crudEx.Features.users.DTO.CreateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.entities.UserEntity;
import com.example.crudEx.Features.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest createUserRequest) {
        UserDTO result = userService.createUser(createUserRequest);
        if (result == null) {
            return ResponseEntity.status(420).body("Si scem");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        boolean result = userService.deleteUser(userId);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("impossible to delete the user");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSingleUser(@PathVariable long userId) {
        UserDTO result = userService.getSingleUser(userId);
        if (result == null) {
            return ResponseEntity.status(422).body("user not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
