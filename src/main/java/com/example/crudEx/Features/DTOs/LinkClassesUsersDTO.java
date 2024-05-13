package com.example.crudEx.Features.DTOs;

import com.example.crudEx.Features.users.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LinkClassesUsersDTO {
    private Long id;

    private UserDTO user;
    private ClassroomDTO classroom;
}
