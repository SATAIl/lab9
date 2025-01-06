package com.example.lab9.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an assignment entity in the system.
 * Assignments are associated with a specific course and can have multiple students.
 */
@Entity
@Table(name = "ASSIGNMENT")
public class Assignment {
    /**
     * The unique identifier for the assignment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the assignment.
     */
    private String title;

    /**
     * The content or description of the assignment.
     */
    private String content;

    /**
     * The course to which the assignment belongs.
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * The list of students who are assigned this task.
     */
    @ManyToMany
    @JoinTable(
            name = "assignment_student",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    /**
     * Default constructor for JPA.
     */
    public Assignment() {}

    /**
     * Constructs a new Assignment with the specified title, content, and course.
     *
     * @param title   the title of the assignment
     * @param content the content of the assignment
     * @param course  the course associated with this assignment
     */
    public Assignment(String title, String content, Course course) {
        this.title = title;
        this.content = content;
        this.course = course;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Course getCourse() {
        return course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Gets the name of the teacher who created the course associated with this assignment.
     *
     * @return the name of the teacher or "Unknown" if no teacher is associated
     */
    public String getTeacherName() {
        return this.course != null && this.course.getTeacher() != null ? this.course.getTeacher().getName() : "Unknown";
    }
}
