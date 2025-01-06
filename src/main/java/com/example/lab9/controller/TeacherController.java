package com.example.lab9.controller;

import com.example.lab9.model.Assignment;
import com.example.lab9.model.Course;
import com.example.lab9.model.Teacher;
import com.example.lab9.service.AssignmentService;
import com.example.lab9.service.CourseService;
import com.example.lab9.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for managing teacher-related operations.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AssignmentService assignmentService;

    /**
     * Registers a new teacher by their name.
     *
     * @param teacherName the name of the teacher
     * @return a message indicating the result of the registration
     */
    @PostMapping("/register")
    public String registerTeacher(@RequestParam String teacherName) {
        Teacher t = teacherService.registerTeacher(teacherName);
        return "Teacher '" + t.getName() + "' registered successfully (ID=" + t.getId() + ")";
    }

    /**
     * Creates a new course for a teacher.
     *
     * @param teacherName the name of the teacher
     * @param courseName  the name of the course
     * @return a message indicating the result of the course creation
     */
    @PostMapping("/createCourse")
    public String createCourse(@RequestParam String teacherName,
                               @RequestParam String courseName) {
        Optional<Teacher> teacherOpt = teacherService.findTeacherByName(teacherName);
        if (teacherOpt.isEmpty()) {
            return "Помилка: вчитель із ім'ям '" + teacherName + "' не знайдено!";
        }
        Teacher teacher = teacherOpt.get();
        Course course = courseService.createCourse(courseName, teacher);
        return "Курс '" + course.getName() + "' створено успішно (ID=" + course.getId() +
                ") вчителем '" + teacher.getName() + "'";
    }

    /**
     * Creates a new assignment for a course by a teacher.
     *
     * @param teacherName the name of the teacher
     * @param courseId    the ID of the course
     * @param title       the title of the assignment
     * @param content     the content of the assignment
     * @return a message indicating the result of the assignment creation
     */
    @PostMapping("/createAssignment")
    public String createAssignment(@RequestParam String teacherName,
                                   @RequestParam Long courseId,
                                   @RequestParam String title,
                                   @RequestParam String content) {
        Optional<Teacher> teacherOpt = teacherService.findTeacherByName(teacherName);
        if (teacherOpt.isEmpty()) {
            return "Помилка: вчитель із ім'ям '" + teacherName + "' не знайдено!";
        }
        Teacher teacher = teacherOpt.get();

        Optional<Course> courseOpt = courseService.findById(courseId);
        if (courseOpt.isEmpty()) {
            return "Помилка: курс з  ID " + courseId + " не знайдено!";
        }
        Course course = courseOpt.get();

        if (!course.getTeacher().getId().equals(teacher.getId())) {
            return "\n" +
                    "Помилка: вчитель '" + teacherName + "' не автор (ID=" + courseId + ")";
        }

        Assignment a = assignmentService.createAssignment(title, content, course);
        return "Завдання '" + a.getTitle() + "' додано до курсу '" + course.getName() + "'.";
    }

    /**
     * Retrieves all assignments created by teachers.
     *
     * @return a list of assignments with their details
     */
    @GetMapping("/assignments")
    public List<Map<String, Object>> getAllAssignments() {
        return assignmentService.findAll().stream().map(a -> {
            Map<String, Object> response = new HashMap<>();
            response.put("title", a.getTitle());
            response.put("content", a.getContent());
            response.put("teacherName", a.getTeacherName());
            return response;
        }).collect(Collectors.toList());
    }
}
