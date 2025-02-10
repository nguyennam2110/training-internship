package com.training.app.entity.mapper;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.model.Student;

public class StudentMapperImpl implements StudentMapper {

  @Override
  public Student toEntity(StudentDTO studentDTO) {
    Student student = new Student();
    student.setName(studentDTO.getName());
    student.setGender(studentDTO.getGender());
    student.setAge(studentDTO.getAge());
    student.setRoomId(studentDTO.getRoomId());
    return student;
  }
}
