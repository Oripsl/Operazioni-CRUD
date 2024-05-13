package com.example.crudEx.Features.classroom.service;

import com.example.crudEx.Features.DTOs.ClassroomDTO;
import com.example.crudEx.Features.DTOs.CreateClassroomRequest;
import com.example.crudEx.Features.DTOs.LinkClassesUsersDTO;
import com.example.crudEx.Features.DTOs.UpdateClassroomRequest;
import com.example.crudEx.Features.classroom.entity.ClassroomEntity;
import com.example.crudEx.Features.classroom.entity.LinkClassesUsersEntity;
import com.example.crudEx.Features.classroom.model.ClassroomModel;
import com.example.crudEx.Features.classroom.repository.ClassroomRepository;
import com.example.crudEx.Features.classroom.repository.LinkClassesUsersRepository;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.Repositories.UserRepository;
import com.example.crudEx.Features.users.entities.UserEntity;
import com.example.crudEx.Features.users.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private LinkClassesUsersRepository linkClassesUsersRepository;

    @Autowired
    private UserRepository userRepository;

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

    public LinkClassesUsersDTO createLink(Long classId, Long userId) {
        Optional<ClassroomEntity> classroomEntity = classroomRepository.findById(classId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if(classroomEntity.isEmpty() || userEntity.isEmpty()) {
            return null;
        } else {
           LinkClassesUsersEntity linkClassesUsersEntity = linkClassesUsersRepository.saveAndFlush(new LinkClassesUsersEntity(classroomEntity.get(), userEntity.get()));
            return new LinkClassesUsersDTO(linkClassesUsersEntity.getId(),  linkClassesUsersEntity.getUserEntities(), linkClassesUsersEntity.getClassroomEntity());
        }
    }

    public List<UserDTO> getClassUsers(Long classId) {
        return linkClassesUsersRepository.findUserEntitiesByClassroomEntity_Id(classId).stream()
                .map(LinkClassesUsersEntity::getUserEntities)
                .map(UserModel::entityToModel)
                .map(UserModel::modelToDto)
                .toList();
    }
// alternativa al get con stream

//    public List<UserDTO> getClassUsersII(Long classId) {
//        List<LinkClassesUsersEntity> classesUsersEntities = linkClassesUsersRepository.findUserEntitiesByClassroomEntity_Id(classId);
//        ArrayList<UserEntity> userEntities = new ArrayList<>();
//        ArrayList<UserModel> userModels = new ArrayList<>();
//        ArrayList<UserDTO> users = new ArrayList<>();
//        for(LinkClassesUsersEntity link : classesUsersEntities){
//            userEntities.add(link.getUserEntities());
//        }
//        for (UserEntity ue : userEntities){
//            userModels.add(UserModel.entityToModel(ue));
//        }
//        for(UserModel um : userModels){
//            users.add(UserModel.modelToDto(um));
//        }
//        return users;
//    }


}
