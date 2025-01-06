package com.example.lab9.service;

import com.example.lab9.model.Teacher;
import com.example.lab9.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing Teacher entities.
 */
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    /**
     * Constructs a new TeacherService.
     *
     * @param teacherRepository the repository for Teacher entities
     */
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * Registers a new teacher with the specified name.
     *
     * @param teacherName the name of the teacher
     * @return the registered teacher
     */
    public Teacher registerTeacher(String teacherName) {
        Teacher t = new Teacher(teacherName);
        return teacherRepository.save(t);
    }

    /**
     * Finds a teacher by their name.
     *
     * @param teacherName the name of the teacher
     * @return an Optional containing the found teacher, or empty if not found
     */
    public Optional<Teacher> findTeacherByName(String teacherName) {
        return teacherRepository.findByName(teacherName);
    }
}
