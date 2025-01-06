package com.example.lab9.repository;

import com.example.lab9.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Course entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}
