package com.example.crudEx.Features.users.service;

import com.example.crudEx.Features.users.DTO.CreateUserRequest;
import com.example.crudEx.Features.users.DTO.UpdateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.model.UserModel;
import com.example.crudEx.Features.users.Repositories.UserRepository;
import com.example.crudEx.Features.users.entities.Role;
import com.example.crudEx.Features.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO createUser(CreateUserRequest createUserRequest) {
        if (createUserRequest.getBirthDate() == null || createUserRequest.getBirthDate().isEmpty()) {
            return null;
        }
        try {
            OffsetDateTime data = OffsetDateTime.parse(createUserRequest.getBirthDate());
            Long age = ChronoUnit.YEARS.between(data, OffsetDateTime.now());
            UserModel userModel = new UserModel(Role.toRole(createUserRequest.getRole()), createUserRequest.getName(), createUserRequest.getSurname(), createUserRequest.getAddress(), data, createUserRequest.getPhone(), age.intValue());
            UserModel userModel1 = UserModel.entityToModel(userRepository.save(UserModel.modelToEntity(userModel)));
            return UserModel.modelToDto(userModel1);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteUser(long userId) {
        Optional<UserEntity> result = userRepository.findById(userId);
        if (result.isPresent()) {
            try {
                userRepository.delete(result.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public UserDTO getSingleUser(long userId) {
        Optional<UserEntity> result = userRepository.findById(userId);
        if (result.isPresent()) {
            UserModel userModel = UserModel.entityToModel(result.get());
            return UserModel.modelToDto(userModel);
        } else {
            return null;
        }
    }

    public UserDTO updateUser(long userId, UpdateUserRequest updateUserRequest) {
        Optional<UserEntity> result = userRepository.findById(userId);

        if (result.isPresent()) {
            long age;
            OffsetDateTime data;
            try {
                data = OffsetDateTime.parse(updateUserRequest.getBirthDate());
                age = ChronoUnit.YEARS.between(data, OffsetDateTime.now());
            } catch (Exception e) {
                data = result.get().getBirthDate();
                age = ChronoUnit.YEARS.between(data, OffsetDateTime.now());
            }

            result.get().setRole(updateUserRequest.getRole() == null ? result.get().getRole() : Role.toRole(updateUserRequest.getRole()));
            result.get().setName(updateUserRequest.getName() == null ? result.get().getName() : updateUserRequest.getName());
            result.get().setSurname(updateUserRequest.getSurname() == null ? result.get().getSurname() : updateUserRequest.getSurname());
            result.get().setAddress(updateUserRequest.getAddress() == null ? result.get().getAddress() : updateUserRequest.getAddress());
            result.get().setAge((int) age);
            result.get().setPhone(updateUserRequest.getPhone() == null ? result.get().getPhone() : updateUserRequest.getPhone());
            result.get().setBirthDate(data);
            UserEntity savedUser = userRepository.saveAndFlush(result.get());
            UserModel savedUserModel = UserModel.entityToModel(savedUser);
            return UserModel.modelToDto(savedUserModel);
        } else {
            return null;
        }
    }


}
