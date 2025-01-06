package com.example.lab9.model;

import jakarta.persistence.*;

/**
 * Represents a teacher entity in the system.
 */
@Entity
@Table(name = "TEACHER")
public class Teacher {
    /**
     * The unique identifier for the teacher.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the teacher.
     */
    private String name;

    /**
     * Default constructor for JPA.
     */
    public Teacher() {}

    /**
     * Constructs a new Teacher with the specified name.
     *
     * @param name the name of the teacher
     */
    public Teacher(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
