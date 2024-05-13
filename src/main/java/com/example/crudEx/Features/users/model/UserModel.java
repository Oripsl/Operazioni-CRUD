package com.example.crudEx.Features.users.model;

import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.entities.Role;
import com.example.crudEx.Features.users.entities.UserEntity;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserModel {
    private Long id;
    private Role role;
    private String name;
    private String surname;
    private String address;
    private OffsetDateTime birthDate;
    private String phone;
    private int age;

    public UserModel(Role role, String name, String surname, String address, OffsetDateTime birthDate, String phone, int age) {
       this.role = role;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.age = age;
    }

    public UserModel(Long id,Role role, String name, String surname, String address, OffsetDateTime birthDate, String phone, int age) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.age = age;
    }

    public static UserDTO modelToDto(UserModel userModel) {
        return new UserDTO(userModel.getId(),userModel.getRole(), userModel.getName(), userModel.getSurname(), userModel.getAddress(), userModel.getBirthDate(), userModel.getPhone(), userModel.getAge());
    }

    public static UserEntity modelToEntity(UserModel userModel) {
        return new UserEntity(userModel.getId(), userModel.getRole(), userModel.getName(), userModel.getSurname(), userModel.getAddress(), userModel.getBirthDate(), userModel.getPhone(), userModel.getAge());
    }

    public static UserModel entityToModel(UserEntity userEntity) {
        return new UserModel(userEntity.getId(),userEntity.getRole(), userEntity.getName(), userEntity.getSurname(), userEntity.getAddress(), userEntity.getBirthDate(), userEntity.getPhone(), userEntity.getAge());
    }

    public static UserModel DtoToModel(UserDTO userDTO) {
        return new UserModel(userDTO.getId(),userDTO.getRole(), userDTO.getName(), userDTO.getSurname(), userDTO.getAddress(), userDTO.getBirthDate(), userDTO.getPhone(), userDTO.getAge());
    }
}
