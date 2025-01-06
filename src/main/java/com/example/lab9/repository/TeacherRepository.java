package com.example.lab9.repository;

import com.example.lab9.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Teacher entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    /**
     * Finds a teacher by their name.
     *
     * @param name the name of the teacher
     * @return an Optional containing the found teacher, or empty if not found
     */
    Optional<Teacher> findByName(String name);
}
