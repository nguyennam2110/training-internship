package com.training.app.entity.mapper;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.model.Student;

import com.training.app.entity.model.Room;

public class StudentMapperImpl implements StudentMapper {

  @Override
  public Student toEntity(StudentDTO studentDTO) {
    Student student = new Student();
    student.setName(studentDTO.getName());
    student.setGender(studentDTO.getGender());
    student.setAge(studentDTO.getAge());

    if (studentDTO.getRoomId() != null) {
      Room room = new Room();
      room.setId(studentDTO.getRoomId());
      student.setRoom(room);
    }

    return student;
  }
}
