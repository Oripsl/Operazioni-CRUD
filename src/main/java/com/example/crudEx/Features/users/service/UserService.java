package com.example.crudEx.Features.users.service;

import com.example.crudEx.Features.users.DTO.CreateUserRequest;
import com.example.crudEx.Features.users.DTO.UpdateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.DTO.UserModel;
import com.example.crudEx.Features.users.Repositories.UserRepository;
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
            UserModel userModel = new UserModel(createUserRequest.getName(), createUserRequest.getSurname(), createUserRequest.getAddress(), data, createUserRequest.getPhone(), age.intValue());
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
            try {
                OffsetDateTime data = OffsetDateTime.parse(updateUserRequest.getBirthDate());
                Long age = ChronoUnit.YEARS.between(data, OffsetDateTime.now());
                UserModel userModel = new UserModel(updateUserRequest.getName(), updateUserRequest.getSurname(), updateUserRequest.getAddress(), data, updateUserRequest.getPhone(), age.intValue());
                UserEntity userEntityUpdate = userRepository.save(UserModel.modelToEntity(userModel));
                UserModel savedUserModel = UserModel.entityToModel(userEntityUpdate);
                return UserModel.modelToDto(savedUserModel);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
