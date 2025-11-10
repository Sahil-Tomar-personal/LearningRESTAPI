package com.tomar.sahil.LearningRESTAPI.controller;

import com.tomar.sahil.LearningRESTAPI.dto.AddStudentDto;
import com.tomar.sahil.LearningRESTAPI.dto.StudentDto;
import com.tomar.sahil.LearningRESTAPI.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent() {
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody AddStudentDto newStudentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(newStudentDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }
}
