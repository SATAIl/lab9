package com.example.lab9.repository;

import com.example.lab9.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Student entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * Finds a student by their name.
     *
     * @param name the name of the student
     * @return an Optional containing the found student, or empty if not found
     */
    Optional<Student> findByName(String name);
}
