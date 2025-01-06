package com.example.lab9.service;

import com.example.lab9.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import com.example.lab9.model.*;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Assignment entities.
 */
@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    /**
     * Constructs a new AssignmentService.
     *
     * @param assignmentRepository the repository for Assignment entities
     */
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Finds an assignment by its ID.
     *
     * @param assignmentId the ID of the assignment
     * @return an Optional containing the found assignment, or empty if not found
     */
    public Optional<Assignment> findById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId);
    }

    /**
     * Creates a new assignment with the specified details.
     *
     * @param title   the title of the assignment
     * @param content the content of the assignment
     * @param course  the course associated with the assignment
     * @return the created assignment
     */
    public Assignment createAssignment(String title, String content, Course course) {
        Assignment a = new Assignment(title, content, course);
        return assignmentRepository.save(a);
    }

    /**
     * Marks an assignment as completed by a student.
     *
     * @param assignmentId the ID of the assignment
     * @param student      the student completing the assignment
     * @return an Optional containing the updated assignment, or empty if not updated
     */
    public Optional<Assignment> completeAssignment(Long assignmentId, Student student) {
        Optional<Assignment> opt = assignmentRepository.findById(assignmentId);
        if (opt.isPresent()) {
            Assignment assignment = opt.get();
            if (!assignment.getStudents().contains(student)) {
                assignment.getStudents().add(student);
                assignmentRepository.save(assignment);
            }
            return Optional.of(assignment);
        }
        return Optional.empty();
    }

    /**
     * Retrieves all assignments.
     *
     * @return a list of all assignments
     */
    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }
}
