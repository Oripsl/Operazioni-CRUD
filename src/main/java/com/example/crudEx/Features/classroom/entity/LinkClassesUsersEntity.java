package com.example.crudEx.Features.classroom.entity;

import com.example.crudEx.Features.users.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "link_classes_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkClassesUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "class_id")
    private ClassroomEntity classroomEntity;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntities;

    public LinkClassesUsersEntity(ClassroomEntity classroomEntity, UserEntity userEntities) {
        this.classroomEntity = classroomEntity;
        this.userEntities = userEntities;
    }

}
