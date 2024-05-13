package com.example.crudEx.Features.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private long id;
    private String name;
    private String description;
}
