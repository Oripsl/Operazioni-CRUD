package com.example.crudEx.Features.users.service;

import com.example.crudEx.Features.users.DTO.CreateUserRequest;
import com.example.crudEx.Features.users.DTO.UserDTO;
import com.example.crudEx.Features.users.DTO.UserModel;
import com.example.crudEx.Features.users.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

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
            UserModel userModel = new UserModel(createUserRequest.getName(), createUserRequest.getSurname(), createUserRequest.getAddress(),data, createUserRequest.getPhone(), age.intValue());
            UserModel userModel1 =  UserModel.entityToModel(userRepository.save(UserModel.modelToEntity(userModel)));
            return UserModel.modelToDto(userModel1);
        }
        catch (Exception e) {
            return null;
        }
    }
}
