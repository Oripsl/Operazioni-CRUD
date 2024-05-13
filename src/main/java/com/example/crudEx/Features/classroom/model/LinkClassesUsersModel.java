package com.example.crudEx.Features.classroom.model;
import com.example.crudEx.Features.DTOs.LinkClassesUsersDTO;
import com.example.crudEx.Features.classroom.entity.LinkClassesUsersEntity;
import com.example.crudEx.Features.users.model.UserModel;
import lombok.Data;

@Data
public class LinkClassesUsersModel{
    private Long id;
    private ClassroomModel classroom;
    private UserModel user;

    public LinkClassesUsersModel(Long id, ClassroomModel classroom, UserModel user) {
        this.id = id;
        this.classroom = classroom;
        this.user = user;
    }

    public LinkClassesUsersModel(ClassroomModel classroom, UserModel user) {
        this.classroom = classroom;
        this.user = user;
    }

    public static LinkClassesUsersDTO modelToDto(LinkClassesUsersModel linkClassesUser) {
        return new LinkClassesUsersDTO(linkClassesUser.getId(), UserModel.modelToDto(linkClassesUser.getUser()),ClassroomModel.modelToDto(linkClassesUser.getClassroom()));
    }

    public static LinkClassesUsersEntity modelToEntity(LinkClassesUsersModel linkClassesUsersModel) {
        return new LinkClassesUsersEntity(linkClassesUsersModel.getId(),ClassroomModel.modelToEntity(linkClassesUsersModel.getClassroom()),UserModel.modelToEntity(linkClassesUsersModel.getUser()));
    }

    public static LinkClassesUsersModel entityToModel(LinkClassesUsersEntity linkClassesUsersEntity) {
        return new LinkClassesUsersModel(linkClassesUsersEntity.getId(),ClassroomModel.entityToModel(linkClassesUsersEntity.getClassroomEntity()),UserModel.entityToModel(linkClassesUsersEntity.getUserEntities()));
    }

    public static LinkClassesUsersModel DtoToModel(LinkClassesUsersDTO linkClassesUsersDTO) {
        return new LinkClassesUsersModel(linkClassesUsersDTO.getId(),ClassroomModel.DtoToModel(linkClassesUsersDTO.getClassroom()),UserModel.DtoToModel(linkClassesUsersDTO.getUser()));
    }
}
