package com.example.lab9.service;

import com.example.lab9.model.Course;
import com.example.lab9.model.Student;
import com.example.lab9.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Student entities.
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    /**
     * Constructs a new StudentService.
     *
     * @param studentRepository the repository for Student entities
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Registers a new student with the given name.
     *
     * @param name the name of the student
     * @return the registered student
     */
    public Student registerStudent(String name) {
        Student s = new Student(name);
        return studentRepository.save(s);
    }

    /**
     * Finds a student by their name.
     *
     * @param name the name of the student
     * @return an Optional containing the found student, or empty if not found
     */
    public Optional<Student> findStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    /**
     * Retrieves all students.
     *
     * @return a list of all students
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Enrolls a student in a course.
     *
     * @param student the student to enroll
     * @param course  the course to enroll in
     * @return the updated student entity
     */
    public Student enrollInCourse(Student student, Course course) {
        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            return studentRepository.save(student);
        }
        return student;
    }
}
