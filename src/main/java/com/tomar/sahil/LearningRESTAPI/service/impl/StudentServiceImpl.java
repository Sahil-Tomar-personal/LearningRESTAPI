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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto updateStudentPartial(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("student with id: " + id + " not found"));
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("field: " + key + " not supported");
            }
        });
        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

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
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("student with id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentDto newStudentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("student with id: " + id + " not found"));
        modelMapper.map(newStudentDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }
}
