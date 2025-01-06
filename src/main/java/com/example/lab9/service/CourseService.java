package com.example.lab9.service;

import com.example.lab9.model.Course;
import com.example.lab9.model.Teacher;
import com.example.lab9.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Course entities.
 */
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    /**
     * Constructs a new CourseService.
     *
     * @param courseRepository the repository for Course entities
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Creates a new course with the specified name and teacher.
     *
     * @param courseName the name of the course
     * @param teacher    the teacher who will be associated with the course
     * @return the created course
     */
    public Course createCourse(String courseName, Teacher teacher) {
        Course c = new Course(courseName, teacher);
        return courseRepository.save(c);
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * Finds a course by its ID.
     *
     * @param courseId the ID of the course
     * @return an Optional containing the found course, or empty if not found
     */
    public Optional<Course> findById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
