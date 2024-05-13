package com.example.crudEx.Features.classroom.repository;

import com.example.crudEx.Features.classroom.entity.LinkClassesUsersEntity;

import jakarta.persistence.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkClassesUsersRepository extends JpaRepository<LinkClassesUsersEntity, Long> {
    List<LinkClassesUsersEntity> findUserEntitiesByClassroomEntity_Id(Long classId);

    @Query("SELECT lcu FROM LinkClassesUsersEntity lcu WHERE lcu.userEntities.Id = :userId AND lcu.classroomEntity.Id = :classId")
    LinkClassesUsersEntity findByUserIdAndClassId(@Param("userId") Long userId, @Param("classId") Long classId);
}
