package com.example.lab9.repository;

import com.example.lab9.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Assignment entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
