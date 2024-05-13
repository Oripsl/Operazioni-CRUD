package com.example.crudEx.Features.classroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classrooms")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;


}
