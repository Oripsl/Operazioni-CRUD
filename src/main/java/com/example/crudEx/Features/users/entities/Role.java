package com.example.crudEx.Features.users.entities;

public enum Role {
    Student, Teacher, NotSet;

    public static Role toRole(String role) {
        return switch (role.toLowerCase()) {
            case "teacher" -> Role.Teacher;
            case "student" -> Role.Student;
            default -> Role.NotSet;
        };
    }
}
