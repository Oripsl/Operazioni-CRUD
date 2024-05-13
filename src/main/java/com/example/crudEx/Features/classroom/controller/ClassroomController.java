package com.example.crudEx.Features.classroom.controller;

import com.example.crudEx.Features.DTOs.ClassroomDTO;
import com.example.crudEx.Features.DTOs.CreateClassroomRequest;
import com.example.crudEx.Features.DTOs.UpdateClassroomRequest;
import com.example.crudEx.Features.classroom.ClassroomService;
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
}
