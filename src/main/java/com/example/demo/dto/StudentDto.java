package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDto {
    private long rollNum;

    @NotBlank(message = "Name Number Required")
    @Size(min = 3,max = 30,message = "Minimum 3 Charters to Maximum 30")
    private String name;

    @Size(min = 10,max = 10)
    @NotBlank(message = "Phone Number Required")
    private long phoneNum;
}
