package com.training.app.dao;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.model.Student;

public interface StudentDAO {
  int createStudent(StudentDTO studentRequest);
  Student searchStudent(StudentDTO studentDTO);
}
