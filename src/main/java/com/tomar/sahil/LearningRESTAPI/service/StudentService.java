package com.tomar.sahil.LearningRESTAPI.service;

import com.tomar.sahil.LearningRESTAPI.dto.AddStudentDto;
import com.tomar.sahil.LearningRESTAPI.dto.StudentDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getAllStudents();
    public StudentDto getStudentById(Long id);
    StudentDto createNewStudent(AddStudentDto newStudentDto);
    void deleteById(Long id);
    StudentDto updateStudent(Long id, AddStudentDto newStudentDto);
}
