package com.klu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {

        Course savedCourse = service.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        List<Course> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {

        Optional<Course> course = service.getCourseById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {

        try {

            Course updatedCourse = service.updateCourse(id, course);
            return ResponseEntity.ok(updatedCourse);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found for update");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        try {

            service.deleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourse(@PathVariable String title) {

        List<Course> courses = service.searchByTitle(title);

        if (courses.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No courses found");
        }

        return ResponseEntity.ok(courses);
    }
}