package com.example.crudEx.Features.classroom.repository;

import com.example.crudEx.Features.classroom.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
}
