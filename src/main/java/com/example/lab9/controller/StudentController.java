package com.example.lab9.controller;

import com.example.lab9.model.Assignment;
import com.example.lab9.model.Course;
import com.example.lab9.model.Student;
import com.example.lab9.service.AssignmentService;
import com.example.lab9.service.CourseService;
import com.example.lab9.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for managing student-related operations.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AssignmentService assignmentService;

    /**
     * Registers a new student with the given name.
     *
     * @param name the name of the student
     * @return a message indicating the result of the registration
     */
    @PostMapping("/register")
    public String registerStudent(@RequestParam String name) {
        Student s = studentService.registerStudent(name);
        return "Student '" + s.getName() + "' registered successfully (ID=" + s.getId() + ")";
    }

    /**
     * Enrolls a student in a course.
     *
     * @param studentName the name of the student
     * @param courseId    the ID of the course
     * @return a message indicating the result of the enrollment
     */
    @PostMapping("/enroll")
    public String enrollInCourse(@RequestParam String studentName, @RequestParam Long courseId) {
        Optional<Student> studentOpt = studentService.findStudentByName(studentName);
        if (studentOpt.isEmpty()) {
            return "Error: Student with name '" + studentName + "' not found";
        }
        Optional<Course> courseOpt = courseService.findById(courseId);
        if (courseOpt.isEmpty()) {
            return "Error: Course with ID " + courseId + " not found";
        }

        Student student = studentOpt.get();
        Course course = courseOpt.get();
        studentService.enrollInCourse(student, course);

        return "Student '" + student.getName() + "' enrolled in course '" + course.getName() + "'";
    }

    /**
     * Completes an assignment for a student.
     *
     * @param studentName  the name of the student
     * @param assignmentId the ID of the assignment
     * @return a message indicating the result of the completion
     */
    @PostMapping("/completeAssignment")
    public String completeAssignment(@RequestParam String studentName, @RequestParam Long assignmentId) {
        Optional<Student> studentOpt = studentService.findStudentByName(studentName);
        if (studentOpt.isEmpty()) {
            return "Помилка: учень із ім'ям '" + studentName + "' не знайдено";
        }
        Student student = studentOpt.get();

        Optional<Assignment> assignmentOpt = assignmentService.findById(assignmentId);
        if (assignmentOpt.isEmpty()) {
            return "Помилка: призначення з ідентифікатором " + assignmentId + " не знайдено";
        }
        Assignment assignment = assignmentOpt.get();

        if (!student.getCourses().contains(assignment.getCourse())) {
            return "Помилка: студент '" + studentName + "'не зарахований на курс '" + assignment.getCourse().getName() + "'";
        }

        Optional<Assignment> updated = assignmentService.completeAssignment(assignmentId, student);
        if (updated.isPresent()) {
            return "Призначення '" + updated.get().getTitle() + "' виконано студентом '" + studentName + "'";
        } else {
            return "Помилка: призначення з ID " + assignmentId + " не було завершено.";
        }
    }

    /**
     * Retrieves a list of students and their enrollments in courses.
     *
     * @return a list of student-course mappings
     */
    @GetMapping("/enrollments")
    public List<Map<String, String>> getStudentEnrollments() {
        List<Map<String, String>> result = new ArrayList<>();

        List<Student> students = studentService.findAll();
        for (Student student : students) {
            for (Course course : student.getCourses()) {
                Map<String, String> enrollment = new HashMap<>();
                enrollment.put("studentName", student.getName());
                enrollment.put("courseName", course.getName());
                result.add(enrollment);
            }
        }
        return result;
    }

    /**
     * Retrieves all available courses.
     *
     * @return a list of courses with their details
     */
    @GetMapping("/courses")
    public List<Map<String, Object>> getAllCourses() {
        return courseService.findAll().stream().map(course -> {
            Map<String, Object> courseData = new HashMap<>();
            courseData.put("name", course.getName());
            courseData.put("id", course.getId());
            courseData.put("teacherName", course.getTeacher().getName());
            return courseData;
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves all assignments across all courses.
     *
     * @return a list of assignments with their details
     */
    @GetMapping("/assignments")
    public List<Map<String, Object>> getAllAssignments() {
        return assignmentService.findAll().stream().map(assignment -> {
            Map<String, Object> assignmentData = new HashMap<>();
            assignmentData.put("courseName", assignment.getCourse().getName());
            assignmentData.put("title", assignment.getTitle());
            assignmentData.put("id", assignment.getId());
            return assignmentData;
        }).collect(Collectors.toList());
    }
}
