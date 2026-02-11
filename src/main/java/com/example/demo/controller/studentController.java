package com.example.demo.controller;
import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.JpaConflictUpdateAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class studentController {


    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{rollNum}")
        public ResponseEntity<StudentDto>getStudentById(@PathVariable long rollNum){
            return ResponseEntity.ok(studentService.getStudentById(rollNum));
        }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{rollNum}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable long rollNum){
        studentService.deleteStudentById(rollNum);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{rollNum}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long rollNum ,
                                                    @RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(rollNum,addStudentRequestDto));
    }

    @PatchMapping("/{rollNum}")
    public ResponseEntity<StudentDto> partialUpdateStudent(@PathVariable Long rollNum,
                                                           @RequestBody @Valid Map<String , Object> updates){
        return ResponseEntity.ok(studentService.partialUpdateStudent(rollNum,updates));
    }

    }



