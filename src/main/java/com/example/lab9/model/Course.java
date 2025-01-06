package com.example.lab9.model;

import jakarta.persistence.*;

/**
 * Represents a course entity in the system.
 * A course is associated with a teacher.
 */
@Entity
@Table(name = "COURSE")
public class Course {
    /**
     * The unique identifier for the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the course.
     */
    private String name;

    /**
     * The teacher who created the course.
     */
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /**
     * Default constructor for JPA.
     */
    public Course() {}

    /**
     * Constructs a new Course with the specified name and teacher.
     *
     * @param name    the name of the course
     * @param teacher the teacher associated with this course
     */
    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getTeacherName() {
        return teacher != null ? teacher.getName() : "Unknown Teacher";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
