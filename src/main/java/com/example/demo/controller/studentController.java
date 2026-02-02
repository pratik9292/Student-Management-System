package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class studentController {
    @GetMapping("/student")
    public StudentDto getStudentDto(){
        return new StudentDto(22231164,"Pratik",8014151007L);
    }
}
