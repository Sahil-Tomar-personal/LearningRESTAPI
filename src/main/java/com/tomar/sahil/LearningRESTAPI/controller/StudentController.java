package com.tomar.sahil.LearningRESTAPI.controller;

import com.tomar.sahil.LearningRESTAPI.dto.StudentDto;
import com.tomar.sahil.LearningRESTAPI.entity.Student;
import com.tomar.sahil.LearningRESTAPI.repository.StudentRepository;
import com.tomar.sahil.LearningRESTAPI.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// This makes Spring Boot handle the bean on its own (as it is a component too)
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getStudent() {
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
