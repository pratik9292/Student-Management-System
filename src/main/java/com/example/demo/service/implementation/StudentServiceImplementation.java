package com.example.demo.service.implementation;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.studentEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository ;
    private final ModelMapper modelMapper;


    @Override
    public List<StudentDto> getAllStudents() {
        List<studentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentDtoList = studentEntities
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity,StudentDto.class))
                .toList();

        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(long rollNum) {
        studentEntity StudentEntity = studentRepository.findById(rollNum).orElseThrow(()-> new IllegalArgumentException("Student Not Found with Roll Number :"+ rollNum));
        return modelMapper.map(StudentEntity , StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        studentEntity newStudent = modelMapper.map(addStudentRequestDto, studentEntity.class);
        studentEntity student = studentRepository.save(newStudent);
        return modelMapper.map(student , StudentDto.class);
    }

    @Override
    public void deleteStudentById(long rollNum) {
        if(!studentRepository.existsById(rollNum)){
            throw new IllegalArgumentException("Student Dosent Exsist by Roll NUmber :"+rollNum);
        }
        studentRepository.deleteById(rollNum);

    }

    @Override
    public StudentDto updateStudent(Long rollNum, AddStudentRequestDto addStudentRequestDto) {
        studentEntity StudentEntity = studentRepository.findById(rollNum)
                .orElseThrow(()-> new IllegalArgumentException("Student Not Found with Roll Number :"+ rollNum));

        modelMapper.map(addStudentRequestDto, StudentEntity);
        studentRepository.save(StudentEntity);

        return modelMapper.map(StudentEntity,StudentDto.class);
    }

    @Override
    public StudentDto partialUpdateStudent(Long rollNum, Map<String, Object> updates) {
        studentEntity StudentEntity = studentRepository.findById(rollNum)
                .orElseThrow(()->new IllegalArgumentException("Student Not Found with Roll Number : "+ rollNum));
      updates.forEach((field,value)->{
          switch(field){
              case "name":StudentEntity.setName((String) value);
                break;
              case "phoneNum":StudentEntity.setPhoneNum((long) value);
                break;

              default:
                  throw new IllegalArgumentException(("Cannot Update Desired Field "));
          }
      });

        studentEntity savedStudent = studentRepository.save(StudentEntity);
        return modelMapper.map(savedStudent , StudentDto.class);
    }


}



