package com.tomar.sahil.LearningRESTAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddStudentDto {
    private String name;
    private String email;
}
