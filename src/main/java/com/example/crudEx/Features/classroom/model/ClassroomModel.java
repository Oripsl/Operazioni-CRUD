package com.example.crudEx.Features.classroom.model;

import com.example.crudEx.Features.DTOs.ClassroomDTO;
import com.example.crudEx.Features.classroom.entity.ClassroomEntity;
import lombok.Data;

@Data
public class ClassroomModel {
    private long id;
    private String name;
    private String description;

    public ClassroomModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ClassroomModel(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ClassroomDTO modelToDto(ClassroomModel classroomModel) {
        return new ClassroomDTO(classroomModel.getId(), classroomModel.getName(), classroomModel.getDescription());
    }

    public static ClassroomEntity modelToEntity(ClassroomModel classroomModel) {
        return new ClassroomEntity(classroomModel.getId(), classroomModel.getName(), classroomModel.getDescription());
    }

    public static ClassroomModel entityToModel(ClassroomEntity classroomEntity) {
        return new ClassroomModel(classroomEntity.getId(), classroomEntity.getName(), classroomEntity.getDescription());
    }

    public static ClassroomModel DtoToModel(ClassroomDTO classroomDTO) {
        return new ClassroomModel(classroomDTO.getId(), classroomDTO.getName(), classroomDTO.getDescription());
    }
}
