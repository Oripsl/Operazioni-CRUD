package com.example.crudEx.Features.classroom;

import com.example.crudEx.Features.DTOs.ClassroomDTO;
import com.example.crudEx.Features.DTOs.CreateClassroomRequest;
import com.example.crudEx.Features.DTOs.UpdateClassroomRequest;
import com.example.crudEx.Features.classroom.entity.ClassroomEntity;
import com.example.crudEx.Features.classroom.model.ClassroomModel;
import com.example.crudEx.Features.classroom.repository.ClassroomRepository;
import com.example.crudEx.Features.users.DTO.UpdateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.entities.Role;
import com.example.crudEx.Features.users.entities.UserEntity;
import com.example.crudEx.Features.users.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public ClassroomDTO createClassroom(CreateClassroomRequest createClassroomRequest) {
        ClassroomModel classroomModel = new ClassroomModel(createClassroomRequest.getName(), createClassroomRequest.getDescription());
        ClassroomModel classroomModel1 = ClassroomModel.entityToModel(classroomRepository.save(ClassroomModel.modelToEntity(classroomModel)));
        return ClassroomModel.modelToDto(classroomModel1);

    }

    public boolean deleteClassroom(long classroomId) {
        Optional<ClassroomEntity> result = classroomRepository.findById(classroomId);
        if (result.isPresent()) {
            try {
                classroomRepository.delete(result.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public ClassroomDTO updateClassroom(long classroomId, UpdateClassroomRequest updateClassroomRequest) {
        Optional<ClassroomEntity> result = classroomRepository.findById(classroomId);

        if (result.isPresent()) {

            result.get().setName(updateClassroomRequest.getName() == null ? result.get().getName() : updateClassroomRequest.getName());
            result.get().setDescription(updateClassroomRequest.getDescription() == null ? result.get().getDescription() : updateClassroomRequest.getDescription());

            ClassroomEntity savedClassroom = classroomRepository.saveAndFlush(result.get());
            ClassroomModel savedClassroomModel = ClassroomModel.entityToModel(savedClassroom);
            return ClassroomModel.modelToDto(savedClassroomModel);
        } else {
            return null;
        }
    }

    public ClassroomDTO getClassroom(long classroomId) {
        Optional<ClassroomEntity> result = classroomRepository.findById(classroomId);
        if (result.isPresent()) {
            ClassroomModel classroomModel = ClassroomModel.entityToModel(result.get());
            return ClassroomModel.modelToDto(classroomModel);
        } else {
            return null;
        }
    }

    public List<ClassroomDTO> getAll() {
        List<ClassroomEntity> classroomEntities = classroomRepository.findAll();
        ArrayList<ClassroomDTO> classroomDTOS = new ArrayList<>();

        for (ClassroomEntity classroomEntity : classroomEntities) {
            ClassroomModel classroomModel = ClassroomModel.entityToModel(classroomEntity);
            ClassroomDTO classroomDTO = ClassroomModel.modelToDto(classroomModel);
            classroomDTOS.add(classroomDTO);
        }
        return classroomDTOS;
    }

}
