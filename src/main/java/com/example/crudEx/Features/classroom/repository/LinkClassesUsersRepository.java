package com.example.crudEx.Features.classroom.repository;

import com.example.crudEx.Features.classroom.entity.LinkClassesUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkClassesUsersRepository extends JpaRepository<LinkClassesUsersEntity, Long> {
    List<LinkClassesUsersEntity> findUserEntitiesByClassroomEntity_Id(Long classId);
}
