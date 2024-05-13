package com.example.crudEx.Features.classroom.controller;

import com.example.crudEx.Features.DTOs.ClassroomDTO;
import com.example.crudEx.Features.DTOs.CreateClassroomRequest;
import com.example.crudEx.Features.DTOs.LinkClassesUsersDTO;
import com.example.crudEx.Features.DTOs.UpdateClassroomRequest;
import com.example.crudEx.Features.classroom.service.ClassroomService;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v2/classrooms/")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    @PostMapping("/create")
    public ResponseEntity<?> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest) {
        ClassroomDTO result = classroomService.createClassroom(createClassroomRequest);
        if (result == null) {
            return ResponseEntity.status(420).body("Si scem");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/delete/classroom/{classroomId}")
    public ResponseEntity<?> deleteClassroom(@PathVariable long classroomId) {
        boolean result = classroomService.deleteClassroom(classroomId);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("impossible to delete the classroom");
        }
    }

    @PatchMapping("/update/classroom/{classroomId}")
    public ResponseEntity<?> updateClassroom(@PathVariable long classroomId, @RequestBody UpdateClassroomRequest updateClassroomRequest) {
        ClassroomDTO result = classroomService.updateClassroom(classroomId, updateClassroomRequest);
        if (result == null) {
            return ResponseEntity.status(422).body("classroom not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/get/{classroomId}")
    public ResponseEntity<?> getClassroom(@PathVariable long classroomId) {
        ClassroomDTO result = classroomService.getClassroom(classroomId);
        if (result == null) {
            return ResponseEntity.status(422).body("classroom not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        List<ClassroomDTO> result = classroomService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/class/addUser")
    public ResponseEntity<?> addUserToClass(@RequestParam Long userId, @RequestParam Long classId) {
        LinkClassesUsersDTO result = classroomService.createLink(userId, classId);
        if (result == null) {
            return ResponseEntity.status(422).body("class or user not found");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/class/getUsers")
    public ResponseEntity<?> getClassUsers(@RequestParam Long classId) {
        List<UserDTO> users = classroomService.getClassUsers(classId);
        if(users.isEmpty()) {
            return ResponseEntity.status(422).body(" users not found for specified class");
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @DeleteMapping("/class/{classId}/removeUser/{userId}")
    public ResponseEntity<?> removeUserFromClass(@PathVariable Long userId, @PathVariable Long classId){
        boolean result = classroomService.removeUserFromClass(userId, classId);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("impossible to delete the classroom");
        }
    }

}
