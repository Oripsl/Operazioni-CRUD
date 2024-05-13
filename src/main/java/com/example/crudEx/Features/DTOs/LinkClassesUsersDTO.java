package com.example.crudEx.Features.DTOs;

import com.example.crudEx.Features.classroom.entity.ClassroomEntity;
import com.example.crudEx.Features.users.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LinkClassesUsersDTO {
    private Long id;

    private UserEntity user;
    private ClassroomEntity classroomEntity;
}
