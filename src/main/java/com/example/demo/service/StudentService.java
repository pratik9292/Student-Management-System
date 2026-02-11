package com.example.demo.service;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();
    StudentDto getStudentById(long rollNum);
    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(long rollNum);


    StudentDto updateStudent(Long rollNum, AddStudentRequestDto addStudentRequestDto);

    StudentDto partialUpdateStudent(Long rollNum, Map<String , Object> updates );
}
