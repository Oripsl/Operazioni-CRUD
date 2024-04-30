package com.example.crudEx.Features.users.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String address;
    private OffsetDateTime birthDate;
    private String phone;
    private int age;


}
