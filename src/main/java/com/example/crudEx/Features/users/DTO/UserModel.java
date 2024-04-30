package com.example.crudEx.Features.users.DTO;

import com.example.crudEx.Features.users.entities.UserEntity;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserModel {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private OffsetDateTime birthDate;
    private String phone;
    private int age;

    public UserModel(String name, String surname, String address, OffsetDateTime birthDate, String phone, int age) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.age = age;
    }

    public UserModel(Long id, String name, String surname, String address, OffsetDateTime birthDate, String phone, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.age = age;
    }

    public static UserDTO modelToDto(UserModel userModel) {
        return new UserDTO(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getAddress(), userModel.getBirthDate(), userModel.getPhone(), userModel.getAge());
    }

    public static UserEntity modelToEntity(UserModel userModel) {
        return new UserEntity(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getAddress(), userModel.getBirthDate(), userModel.getPhone(), userModel.getAge());
    }

    public static UserModel entityToModel(UserEntity userEntity) {
        return new UserModel(userEntity.getId(), userEntity.getName(), userEntity.getSurname(), userEntity.getAddress(), userEntity.getBirthDate(), userEntity.getPhone(), userEntity.getAge());
    }

    public static UserModel DtoToModel(UserDTO userDTO) {
        return new UserModel(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), userDTO.getAddress(), userDTO.getBirthDate(), userDTO.getPhone(), userDTO.getAge());
    }
}
