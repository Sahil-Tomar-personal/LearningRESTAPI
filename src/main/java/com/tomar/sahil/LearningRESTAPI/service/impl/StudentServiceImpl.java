package com.tomar.sahil.LearningRESTAPI.service.impl;

import com.tomar.sahil.LearningRESTAPI.dto.AddStudentDto;
import com.tomar.sahil.LearningRESTAPI.dto.StudentDto;
import com.tomar.sahil.LearningRESTAPI.entity.Student;
import com.tomar.sahil.LearningRESTAPI.repository.StudentRepository;
import com.tomar.sahil.LearningRESTAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentsPOJO= studentRepository.findAll();
        return studentsPOJO
                .stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return modelMapper.map(studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("couldn't find student with id: " + id)), StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentDto newStudentDto) {
        return modelMapper.map(studentRepository.save(modelMapper.map(newStudentDto, Student.class)), StudentDto.class);
    }

    @Override
    public void deletebyId(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("student with id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }
}
