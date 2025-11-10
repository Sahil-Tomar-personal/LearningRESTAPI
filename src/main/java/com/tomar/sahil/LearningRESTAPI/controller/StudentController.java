package com.tomar.sahil.LearningRESTAPI.controller;

import com.tomar.sahil.LearningRESTAPI.dto.AddStudentDto;
import com.tomar.sahil.LearningRESTAPI.dto.StudentDto;
import com.tomar.sahil.LearningRESTAPI.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable Long id, @RequestBody AddStudentDto newStudentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, newStudentDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchStudentById(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(studentService.updateStudentPartial(id, updates));
    }
}
